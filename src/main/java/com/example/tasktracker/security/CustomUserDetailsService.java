package com.example.tasktracker.security;

import com.example.tasktracker.model.TeamMember;
import com.example.tasktracker.model.User;
import com.example.tasktracker.repository.TeamMemberRepository;
import com.example.tasktracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final TeamMemberRepository teamMemberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        List<TeamMember> teamRoles = teamMemberRepository.getTeamMembersByUserId(user.getId());

        return new CustomUserDetails(user, teamRoles);
    }

}
