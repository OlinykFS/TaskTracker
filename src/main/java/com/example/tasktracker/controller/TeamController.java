package com.example.tasktracker.controller;

import com.example.tasktracker.dto.teamDtos.TeamCreateDTO;
import com.example.tasktracker.model.Team;
import com.example.tasktracker.service.teamService.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping("/new")
    public void createTeam(@RequestBody TeamCreateDTO teamCreateDTO) {
        teamService.createTeam(teamCreateDTO);
    }

    @GetMapping("/team")
    public List<Team> getAllTeams() {
        return teamService.findAllTeams();
    }

}
