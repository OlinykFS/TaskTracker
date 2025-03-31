package com.example.tasktracker.service.authService;

import com.example.tasktracker.dto.authDtos.LoginRequestDTO;
import com.example.tasktracker.dto.authDtos.LoginResponseDTO;
import com.example.tasktracker.dto.authDtos.RegistrationRequestDTO;
import com.example.tasktracker.dto.authDtos.RegistrationResponseDTO;

public interface AuthService {
    LoginResponseDTO authenticateUser(LoginRequestDTO loginRequest);
    RegistrationResponseDTO registerUser(RegistrationRequestDTO registrationRequest);
}
