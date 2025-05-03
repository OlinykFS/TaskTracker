package com.example.tasktracker.service.teamMembersService;

import com.example.tasktracker.dto.teamMemberDtos.TeamMemberCreateDTO;
import com.example.tasktracker.dto.teamMemberDtos.TeamMemberResponseDTO;

import java.util.List;

public interface TeamMemberService {
    List<TeamMemberResponseDTO> findAllTeamMembersByTeamId(Long teamId);
    TeamMemberResponseDTO addTeamMember(TeamMemberCreateDTO teamMember);
    void deleteTeamMember(Long teamId, Long userId);
    boolean isUserMemberOfTeam(Long teamId, Long userId);
}
