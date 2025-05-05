package com.example.tasktracker.service.authService;

import com.example.tasktracker.dto.authDtos.LoginRequestDTO;
import com.example.tasktracker.dto.authDtos.LoginResponseDTO;
import com.example.tasktracker.dto.authDtos.RegistrationRequestDTO;
import com.example.tasktracker.dto.authDtos.RegistrationResponseDTO;
import com.example.tasktracker.enums.UserRole;
import com.example.tasktracker.exceptions.EmailAlreadyExistsException;
import com.example.tasktracker.model.User;
import com.example.tasktracker.repository.UserRepository;
import com.example.tasktracker.security.jwtUtils.JwtTokenProvider;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public LoginResponseDTO authenticateUser(LoginRequestDTO loginRequest, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        String token = jwtTokenProvider.generateToken(authentication);
        jwtTokenProvider.setJwtCookie(response, token);
        return new LoginResponseDTO("success");
    }

    @Override
    @Transactional
    public RegistrationResponseDTO registerUser(RegistrationRequestDTO registrationRequest) {
        try {
            userRepository.save(new User(
                    null,
                    registrationRequest.email(),
                    passwordEncoder.encode(registrationRequest.password()),
                    registrationRequest.firstName(),
                    registrationRequest.lastName(),
                    UserRole.ROLE_USER
            ));
                return new RegistrationResponseDTO("User registered successfully");
        } catch (DataIntegrityViolationException ex) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
    }

    @Override
    public void logout(HttpServletResponse response) {
        jwtTokenProvider.clearJwtCookie(response);
    }
}
