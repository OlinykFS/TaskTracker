package com.example.tasktracker.service.teamService;

import com.example.tasktracker.dto.teamDtos.TeamCreateDTO;
import com.example.tasktracker.model.Team;
import com.example.tasktracker.repository.TeamRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void createTeam(TeamCreateDTO teamCreateDTO) {
        Team team = objectMapper.convertValue(teamCreateDTO, Team.class);
        teamRepository.save(team);
    }

    @Override
    public List<Team> findAllTeams() {
        return teamRepository.findAll();
    }
}
