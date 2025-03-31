package com.example.tasktracker.service.teamService;

import com.example.tasktracker.dto.teamDtos.TeamCreateDTO;
import com.example.tasktracker.model.Team;
import com.example.tasktracker.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    @Override
    public void createTeam(TeamCreateDTO teamCreateDTO) {
        teamRepository.createTeam(teamCreateDTO);
    }

    @Override
    public Team findTeamById(Long id) {
        return teamRepository.getTeamById(id);
    }

    @Override
    public List<Team> findAllTeams() {
        return teamRepository.getAllTeams();
    }
}
