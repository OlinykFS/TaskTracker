package com.example.tasktracker.security;

import com.example.tasktracker.model.TeamMember;
import com.example.tasktracker.model.User;
import com.example.tasktracker.repository.TeamMemberRepository;
import com.example.tasktracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final TeamMemberRepository teamMemberRepository;

    public Authentication getAuthentication(String token) {
        String email = jwtTokenProvider.getEmailFromToken(token);
        log.info("Extracted email from token: {}", email);

        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        log.info("Loaded user: {}", user.getEmail());

        List<TeamMember> teamRoles = teamMemberRepository.findAllByUserId(user.getId());
        log.info("Loaded team roles: {}", teamRoles);

        CustomUserDetails userDetails = new CustomUserDetails(user, teamRoles);

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

}

