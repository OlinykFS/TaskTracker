package com.example.tasktracker.controller;

import com.example.tasktracker.dto.teamMemberDtos.TeamMemberCreateDTO;
import com.example.tasktracker.dto.teamMemberDtos.TeamMemberResponseDTO;
import com.example.tasktracker.model.TeamMember;
import com.example.tasktracker.service.teamMembersService.TeamMemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/team/members")
@RequiredArgsConstructor
public class TeamMemberController {

    private final TeamMemberService teamMemberService;

    @PostMapping("/new")
    public TeamMember addTeamMember(@Valid @RequestBody TeamMemberCreateDTO teamMember) {
        return teamMemberService.addTeamMember(teamMember);
    }

    @GetMapping("/{teamId}")
    public List<TeamMemberResponseDTO> getAllTeamMembers(@PathVariable Long teamId) {
        return teamMemberService.findAllTeamMembersByTeamId(teamId);
    }

}
