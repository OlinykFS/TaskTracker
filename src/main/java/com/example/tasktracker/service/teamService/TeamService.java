package com.example.tasktracker.service.teamService;

import com.example.tasktracker.dto.teamDtos.TeamCreateDTO;
import com.example.tasktracker.dto.teamDtos.TeamResponseDTO;
import com.example.tasktracker.dto.teamDtos.TeamUpdateDTO;

import java.util.List;

public interface TeamService {
    TeamResponseDTO createTeam(TeamCreateDTO teamCreateDTO);
    List<TeamResponseDTO> findAllTeams();
    TeamResponseDTO findTeamById(Long teamId);
    TeamResponseDTO updateTeam(TeamUpdateDTO teamCreateDTO);
    void deleteTeam(Long id);



}
