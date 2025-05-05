package com.example.tasktracker.controller;


import com.example.tasktracker.dto.authDtos.LoginRequestDTO;
import com.example.tasktracker.dto.authDtos.LoginResponseDTO;
import com.example.tasktracker.dto.authDtos.RegistrationRequestDTO;
import com.example.tasktracker.dto.authDtos.RegistrationResponseDTO;
import com.example.tasktracker.service.authService.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponseDTO> register(@Valid @RequestBody RegistrationRequestDTO registrationRequest) {
        return ResponseEntity.ok(authService.registerUser(registrationRequest));
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequest, HttpServletResponse response) {
        return ResponseEntity.ok(authService.authenticateUser(loginRequest, response));
    }

    @PostMapping("/logout")
    public void logout(HttpServletResponse response) {
        authService.logout(response);
    }

}
