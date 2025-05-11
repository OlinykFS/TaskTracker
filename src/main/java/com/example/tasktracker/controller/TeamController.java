package com.example.tasktracker.controller;

import com.example.tasktracker.dto.teamDtos.TeamCreateDTO;
import com.example.tasktracker.dto.teamDtos.TeamResponseDTO;
import com.example.tasktracker.dto.teamDtos.TeamUpdateDTO;
import com.example.tasktracker.service.teamService.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public ResponseEntity<TeamResponseDTO> createTeam(@RequestBody @Valid TeamCreateDTO teamCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teamService.createTeam(teamCreateDTO));
    }

    @GetMapping
    public List<TeamResponseDTO> getAllTeams() {
        return teamService.findAllTeams();
    }

    @GetMapping("/{id}")
    public TeamResponseDTO getTeamById(@PathVariable Long id) {
        return teamService.findTeamById(id);
    }

    @PutMapping()
    public ResponseEntity<TeamResponseDTO> updateTeam(@RequestBody @Valid TeamUpdateDTO dto) {
        return ResponseEntity.ok(teamService.updateTeam(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

