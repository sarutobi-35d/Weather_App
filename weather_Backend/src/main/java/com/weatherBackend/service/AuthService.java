package com.weatherBackend.service;

import com.weatherBackend.dto.LoginRequestDTO;
import com.weatherBackend.dto.RegisterRequestDTO;
import com.weatherBackend.dto.UserResponseDTO;

public interface AuthService {

    UserResponseDTO register(RegisterRequestDTO userRequestDTO);
    UserResponseDTO login(LoginRequestDTO loginRequestDTO);
}
