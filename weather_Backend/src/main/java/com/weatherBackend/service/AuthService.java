package com.weatherBackend.service;

import com.weatherBackend.dto.LoginRequestDTO;
import com.weatherBackend.dto.UserRequestDTO;
import com.weatherBackend.dto.UserResponseDTO;

public interface AuthService {

    UserResponseDTO register(UserRequestDTO userRequestDTO);
    UserResponseDTO login(LoginRequestDTO loginRequestDTO);
}
