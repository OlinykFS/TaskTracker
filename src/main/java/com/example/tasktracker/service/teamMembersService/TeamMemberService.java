package com.example.tasktracker.service.teamMembersService;

import com.example.tasktracker.model.TeamMember;

import java.util.List;

public interface TeamMemberService {
    List<TeamMember> findAllTeamMembers(Long teamId);
    TeamMember addTeamMember(TeamMember teamMember);
}
