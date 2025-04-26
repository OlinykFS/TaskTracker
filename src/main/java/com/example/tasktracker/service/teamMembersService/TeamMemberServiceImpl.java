package com.example.tasktracker.service.teamMembersService;

import com.example.tasktracker.dto.teamMemberDtos.TeamMemberCreateDTO;
import com.example.tasktracker.model.TeamMember;
import com.example.tasktracker.repository.TeamMemberRepository;
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
    public List<TeamMember> findAllTeamMembers(Long teamId) {
        return teamMemberRepository.getTeamMemberByTeamId(teamId);
    }

    @Override
    public TeamMember addTeamMember(TeamMemberCreateDTO teamMember) {
        TeamMember tm = objectMapper.convertValue(teamMember, TeamMember.class);
        return teamMemberRepository.save(tm);
    }
}
