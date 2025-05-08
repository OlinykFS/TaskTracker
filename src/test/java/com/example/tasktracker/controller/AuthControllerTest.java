package com.example.tasktracker.controller;

import com.example.tasktracker.dto.authDtos.LoginRequestDTO;
import com.example.tasktracker.dto.authDtos.LoginResponseDTO;
import com.example.tasktracker.dto.authDtos.RegistrationRequestDTO;
import com.example.tasktracker.dto.authDtos.RegistrationResponseDTO;
import com.example.tasktracker.exceptions.EmailAlreadyExistsException;
import com.example.tasktracker.security.jwtUtils.JwtAuthenticationService;
import com.example.tasktracker.security.jwtUtils.JwtTokenProvider;
import com.example.tasktracker.service.authService.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthService authService;

    @MockitoBean
    private JwtTokenProvider jwtTokenProvider;

    @MockitoBean
    private JwtAuthenticationService jwtAuthenticationService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void shouldRegisterUserSuccessfully() throws Exception {
        RegistrationRequestDTO registrationRequest = new RegistrationRequestDTO(
                "test@test.com", "password", "test", "test"
        );
        RegistrationResponseDTO registrationResponse = new RegistrationResponseDTO("success");

        when(authService.registerUser(any(RegistrationRequestDTO.class))).thenReturn(registrationResponse);

        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"));
    }

    @Test
    void shouldNotRegisterUserIfUserAlreadyExists() throws Exception {
        RegistrationRequestDTO registrationRequest = new RegistrationRequestDTO("test@test.com", "password", "test", "test");
        when(authService.registerUser(any(RegistrationRequestDTO.class)))
                .thenThrow(new EmailAlreadyExistsException("Email already exists"));
        mockMvc.perform(post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registrationRequest)))
        .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Email already exists: Email already exists"));
    }

    @Test
    void shouldAuthenticateUserSuccessfully() throws Exception {
        LoginRequestDTO loginRequest = new LoginRequestDTO("test@test.com", "password");
        LoginResponseDTO loginResponse = new LoginResponseDTO("success");
        when(authService.authenticateUser(any(LoginRequestDTO.class), any(HttpServletResponse.class)))
                .thenReturn(loginResponse);
        mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"));
    }

    @Test
    void shouldNotAuthenticateUserIfUserDoesNotExist() throws Exception {
        LoginRequestDTO loginRequest = new LoginRequestDTO("test@test.com", "password");
        when(authService.authenticateUser(any(LoginRequestDTO.class), any(HttpServletResponse.class)))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Authentication error: Bad credentials"));
    }

    @Test
    void shouldNotAuthenticateUserIfBadCredential() throws Exception {
        LoginRequestDTO loginRequest = new LoginRequestDTO("badCred@test.com", "password");
        when(authService.authenticateUser(any(LoginRequestDTO.class), any(HttpServletResponse.class)))
                .thenThrow(new BadCredentialsException("Bad credentials"));
        mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Authentication error: Bad credentials"));
    }

    @Test
    void shouldLogoutUserSuccessfully() throws Exception {
        mockMvc.perform(post("/api/v1/auth/logout")).andExpect(status().isOk());
    }
}