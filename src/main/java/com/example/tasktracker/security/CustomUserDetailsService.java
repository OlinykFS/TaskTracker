package com.example.tasktracker.security;

import com.example.tasktracker.model.TeamMember;
import com.example.tasktracker.model.User;
import com.example.tasktracker.repository.TeamMemberRepository;
import com.example.tasktracker.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final TeamMemberRepository teamMemberRepository;

    public CustomUserDetailsService(UserRepository userRepository, TeamMemberRepository teamMemberRepository) {
        this.userRepository = userRepository;
        this.teamMemberRepository = teamMemberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        List<TeamMember> teamRoles = teamMemberRepository.findRolesByUserId(user.getId());

        return new CustomUserDetails(user, teamRoles);
    }
}
