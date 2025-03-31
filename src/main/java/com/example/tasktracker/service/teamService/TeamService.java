package com.example.tasktracker.service.teamService;

import com.example.tasktracker.dto.teamDtos.TeamCreateDTO;
import com.example.tasktracker.model.Team;

import java.util.List;

public interface TeamService {
    Team findTeamById(Long id);
    void createTeam(TeamCreateDTO teamCreateDTO);
    List<Team> findAllTeams();

}
