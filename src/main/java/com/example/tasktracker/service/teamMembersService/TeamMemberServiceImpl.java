package com.example.tasktracker.service.teamMembersService;

import com.example.tasktracker.dto.teamMemberDtos.TeamMemberCreateDTO;
import com.example.tasktracker.dto.teamMemberDtos.TeamMemberResponseDTO;
import com.example.tasktracker.enums.TeamRole;
import com.example.tasktracker.model.TeamMember;
import com.example.tasktracker.repository.TeamMemberRepository;
import com.example.tasktracker.security.TeamAccess;
import com.example.tasktracker.security.TeamId;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamMemberServiceImpl implements TeamMemberService {

    private final TeamMemberRepository teamMemberRepository;
    private final ObjectMapper objectMapper;

    @Override
    @TeamAccess(requiredRole = TeamRole.ROLE_MANAGER)
    public List<TeamMemberResponseDTO> findAllTeamMembersByTeamId(@TeamId Long teamId) {
        return teamMemberRepository.findTeamMembersWithUserDetailsByTeamId(teamId);
    }

    @Override
    public TeamMember addTeamMember(TeamMemberCreateDTO teamMember) {
        TeamMember tm = objectMapper.convertValue(teamMember, TeamMember.class);
        return teamMemberRepository.save(tm);
    }
}
