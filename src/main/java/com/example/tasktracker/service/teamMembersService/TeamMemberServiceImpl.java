package com.example.tasktracker.service.teamMembersService;

import com.example.tasktracker.model.TeamMember;
import com.example.tasktracker.repository.TeamMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamMemberServiceImpl implements TeamMemberService {

    private final TeamMemberRepository teamMemberRepository;

    @Override
    public List<TeamMember> findAllTeamMembers(Long teamId) {
        return teamMemberRepository.getTeamMembersByTeamId(teamId);
    }

    @Override
    public TeamMember addTeamMember(TeamMember teamMember) {
        teamMemberRepository.addUserToTeam(teamMember);
        return teamMember;
    }
}
