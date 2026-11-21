package com.weatherBackend.service.implentation;

import com.weatherBackend.dto.LoginRequestDTO;
import com.weatherBackend.dto.RegisterRequestDTO;
import com.weatherBackend.dto.UserResponseDTO;
import com.weatherBackend.entity.User;
import com.weatherBackend.repository.UserRepository;
import com.weatherBackend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO register(RegisterRequestDTO userRequestDTO) {

        String email = userRequestDTO.getEmail();

        if (userRepository.existsByEmail(email)){
            throw new RuntimeException("Email déjà existant");
        }

        User user = new User();

        user.setName(userRequestDTO.getName());
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));

        return enDTO(userRepository.save(user));
    }

    @Override
    public UserResponseDTO login(LoginRequestDTO loginRequestDTO) {
        return null;
    }

    //MAPPING

    private User enEntity(RegisterRequestDTO requestDTO){

        User user = new User();

        user.setName(requestDTO.getName());
        user.setEmail(requestDTO.getEmail());
        user.setPassword(requestDTO.getPassword());

        return user;
    }

    private UserResponseDTO enDTO(User user){

        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setId(user.getId());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setEmail(user.getEmail());

        return userResponseDTO;
    }
}
