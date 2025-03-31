package com.example.tasktracker.controller;

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
    public void addTeamMember(@Valid @RequestBody TeamMember teamMember) {
        teamMemberService.addTeamMember(teamMember);
    }

    @GetMapping("/{teamId}")
    public List<TeamMember> getAllTeamMembers(@PathVariable Long teamId) {
        return teamMemberService.findAllTeamMembers(teamId);
    }

}
