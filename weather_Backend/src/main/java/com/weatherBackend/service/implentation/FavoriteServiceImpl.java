package com.weatherBackend.service.implentation;

import com.weatherBackend.dto.FavoriteCityRequestDTO;
import com.weatherBackend.dto.FavoriteCityResponseDTO;
import com.weatherBackend.entity.FavoriteCity;
import com.weatherBackend.entity.User;
import com.weatherBackend.exception.FavoriteAlreadyExistsException;
import com.weatherBackend.exception.FavoriteLimitExceededException;
import com.weatherBackend.exception.FavoriteNotFoundException;
import com.weatherBackend.exception.UserNotFoundException;
import com.weatherBackend.repository.FavoriteCityRepository;
import com.weatherBackend.repository.UserRepository;
import com.weatherBackend.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteCityRepository favoriteCityRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public FavoriteCityResponseDTO addFavorite(Long userId, FavoriteCityRequestDTO requestDTO) {

        // Verif que le user existe et verrouiller sa ligne
        User user = userRepository.findByIdForUpdate(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        // Verif si la ville n'existe pas déjà
        if (favoriteCityRepository.existsByUser_IdAndCityId(userId, requestDTO.getCityId())){
            throw new FavoriteAlreadyExistsException(requestDTO.getCityName());
        }

        // Verif la limite de 10 favoris
        if (favoriteCityRepository.countByUser_Id(userId) >= 10){
            throw new FavoriteLimitExceededException();
        }

        //Create le favori
        FavoriteCity favoriteCity = new FavoriteCity();

        favoriteCity.setCityId(requestDTO.getCityId());
        favoriteCity.setCityName(requestDTO.getCityName());
        favoriteCity.setCountry(requestDTO.getCountry());
        favoriteCity.setUser(user);

        //Save
        FavoriteCity savedFavorite = favoriteCityRepository.save(favoriteCity);

        //RETOURNER EN DTO
        return new FavoriteCityResponseDTO(
                savedFavorite.getId(),
                savedFavorite.getCityId(),
                savedFavorite.getCityName(),
                savedFavorite.getCountry()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<FavoriteCityResponseDTO> getFavorites(Long userId) {

        return favoriteCityRepository.findAllByUser_Id(userId)
                .stream()
                .map(favorite -> new FavoriteCityResponseDTO(
                        favorite.getId(),
                        favorite.getCityId(),
                        favorite.getCityName(),
                        favorite.getCountry()
                )).toList();
    }

    @Override
    @Transactional
    public void deleteFavorite(Long favoriteId, Long userId) {

        FavoriteCity favoriteCity = favoriteCityRepository.findByIdAndUser_Id(favoriteId, userId)
                .orElseThrow(() -> new FavoriteNotFoundException(favoriteId));

        favoriteCityRepository.delete(favoriteCity);
    }

    @Override
    public FavoriteCityResponseDTO updateFavorite(Long favoriteId, Long userId, FavoriteCityRequestDTO requestDTO) {

        //Verif que le favori existe et appartient à un user
        FavoriteCity favoriteCity = favoriteCityRepository.findByIdAndUser_Id(favoriteId, userId)
                .orElseThrow(() -> new FavoriteNotFoundException(favoriteId));

        //Verif que la new ville n'est pas déjà dans les favoris du user
        boolean alreadyExists = favoriteCityRepository.existsByUser_IdAndCityIdAndIdNot(userId, requestDTO.getCityId(),
                favoriteId);

        if (alreadyExists){
            throw new FavoriteAlreadyExistsException(requestDTO.getCityName());
        }

        //Mise à jour
        favoriteCity.setCityId(requestDTO.getCityId());
        favoriteCity.setCityName(requestDTO.getCityName());
        favoriteCity.setCountry(requestDTO.getCountry());

        //Save and return DTO
        FavoriteCity updatedFavorite = favoriteCityRepository.save(favoriteCity);

        return new FavoriteCityResponseDTO(
                updatedFavorite.getId(),
                updatedFavorite.getCityId(),
                updatedFavorite.getCityName(),
                updatedFavorite.getCountry()
        );

    }


}
