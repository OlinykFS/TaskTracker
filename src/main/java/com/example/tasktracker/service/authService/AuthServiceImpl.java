package com.example.tasktracker.service.authService;

import com.example.tasktracker.dto.authDtos.LoginRequestDTO;
import com.example.tasktracker.dto.authDtos.LoginResponseDTO;
import com.example.tasktracker.dto.authDtos.RegistrationRequestDTO;
import com.example.tasktracker.dto.authDtos.RegistrationResponseDTO;
import com.example.tasktracker.enums.UserRole;
import com.example.tasktracker.exceptions.EmailAlreadyExistsException;
import com.example.tasktracker.model.User;
import com.example.tasktracker.security.JwtTokenProvider;
import com.example.tasktracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public LoginResponseDTO authenticateUser(LoginRequestDTO loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        String token = tokenProvider.generateToken(authentication);
        return new LoginResponseDTO("Success", token);
    }

    @Override
    @Transactional
    public RegistrationResponseDTO registerUser(RegistrationRequestDTO registrationRequest) {
        String encodedPassword = passwordEncoder.encode(registrationRequest.password());
        User user = new User(
                null,
                registrationRequest.email(),
                encodedPassword,
                registrationRequest.firstName(),
                registrationRequest.lastName(),
                UserRole.ROLE_USER
        );
        try {
            userRepository.save(user);
                return new RegistrationResponseDTO("User registered successfully");
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw new EmailAlreadyExistsException("Email already exists", ex);
        }
    }
}
