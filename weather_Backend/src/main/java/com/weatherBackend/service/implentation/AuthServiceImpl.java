package com.weatherBackend.service.implentation;

import com.weatherBackend.config.CustomUserDetails;
import com.weatherBackend.dto.LoginRequestDTO;
import com.weatherBackend.dto.RegisterRequestDTO;
import com.weatherBackend.dto.UserResponseDTO;
import com.weatherBackend.entity.User;
import com.weatherBackend.exception.EmailAlreadyExistsException;
import com.weatherBackend.repository.UserRepository;
import com.weatherBackend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserResponseDTO register(RegisterRequestDTO userRequestDTO) {

        String email = userRequestDTO.getEmail();

        if (userRepository.existsByEmail(email)){
            throw new EmailAlreadyExistsException(email);
        }

        User user = new User();

        user.setName(userRequestDTO.getName());
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));

        return enDTO(userRepository.save(user));
    }

    @Override
    public UserResponseDTO login(LoginRequestDTO loginRequestDTO) {

        //Creation du token avec les ids fournis
        UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(
                loginRequestDTO.getEmail(),
                loginRequestDTO.getPassword()
        );

        // Authenification par Spring Security
        Authentication authentication = authenticationManager.authenticate(token);

        //Creation du context de securité
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);

        //Recup du user authentifié
        String email = authentication.getName();

        //Recherche du User dans la BD
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable: " + email));

        return enDTO(user);
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
