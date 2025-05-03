package com.example.tasktracker.service.teamMembersService;

import com.example.tasktracker.dto.teamMemberDtos.TeamMemberCreateDTO;
import com.example.tasktracker.dto.teamMemberDtos.TeamMemberResponseDTO;
import com.example.tasktracker.enums.TeamRole;
import com.example.tasktracker.exceptions.TeamIsEmptyException;
import com.example.tasktracker.model.TeamMember;
import com.example.tasktracker.repository.TeamMemberRepository;
import com.example.tasktracker.security.TeamAccess;
import com.example.tasktracker.security.TeamId;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamMemberServiceImpl implements TeamMemberService {

    private final TeamMemberRepository teamMemberRepository;
    private final ObjectMapper objectMapper;

    @Override
    @TeamAccess(requiredRole = TeamRole.ROLE_MANAGER)
    public List<TeamMemberResponseDTO> findAllTeamMembersByTeamId(@TeamId Long teamId) {
        return teamMemberRepository.getTeamMembersWithUserDetailsByTeamId(teamId).orElseThrow
                (() -> new TeamIsEmptyException("the Team is Empty"));
    }

    @Override
    @Transactional
    @TeamAccess(requiredRole = TeamRole.ROLE_MANAGER)
    public TeamMemberResponseDTO addTeamMember(TeamMemberCreateDTO dto) {
        return teamMemberRepository
                .getTeamMemberByTeamIdAndUserId(dto.teamId(), dto.userId())
                .map(existing -> teamMemberRepository.getTeamMemberWithUserDetailsByUserId(dto.userId(), dto.teamId()))
                .orElseGet(() -> {
                    TeamMember newMember = objectMapper.convertValue(dto, TeamMember.class);
                    teamMemberRepository.save(newMember);
                    return teamMemberRepository.getTeamMemberWithUserDetailsByUserId(dto.userId(), dto.teamId());
                });
    }



    @Override
    @TeamAccess(requiredRole = TeamRole.ROLE_MANAGER)
    @Transactional
    public void deleteTeamMember(@TeamId Long teamId, Long userId) {
        TeamMember tm = teamMemberRepository.getTeamMemberByTeamIdAndUserId(teamId, userId)
                .orElseThrow(() -> new UsernameNotFoundException("Team member not found"));
        teamMemberRepository.deleteById(tm.getId());
    }
}
