package com.example.tasktracker.service.authService;

import com.example.tasktracker.dto.authDtos.LoginRequestDTO;
import com.example.tasktracker.dto.authDtos.LoginResponseDTO;
import com.example.tasktracker.dto.authDtos.RegistrationRequestDTO;
import com.example.tasktracker.dto.authDtos.RegistrationResponseDTO;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    LoginResponseDTO authenticateUser(LoginRequestDTO loginRequest, HttpServletResponse response);
    RegistrationResponseDTO registerUser(RegistrationRequestDTO registrationRequest);
    void logout(HttpServletResponse response);
}
