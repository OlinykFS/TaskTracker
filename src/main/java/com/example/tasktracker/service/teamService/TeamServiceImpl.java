package com.example.tasktracker.service.teamService;

import com.example.tasktracker.dto.teamDtos.TeamCreateDTO;
import com.example.tasktracker.dto.teamDtos.TeamResponseDTO;
import com.example.tasktracker.dto.teamDtos.TeamUpdateDTO;
import com.example.tasktracker.model.Team;
import com.example.tasktracker.repository.TeamRepository;
import com.example.tasktracker.security.TeamId;
import com.example.tasktracker.service.userService.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final ObjectMapper objectMapper;
    private final UserService userService;

    @Override
    public void createTeam(TeamCreateDTO teamCreateDTO) {
        Team team = objectMapper.convertValue(teamCreateDTO, Team.class);
        team.setOwnerId(userService.getCurrentUserId());
        teamRepository.save(team);
    }

    @Override
    public List<TeamResponseDTO> findAllTeams() {
        return teamRepository.getAllTeams();
    }

    @Override
    public TeamResponseDTO findTeamById(@TeamId Long teamId) {
        return teamRepository.getTeamById(teamId);
    }

    @Override
    public TeamResponseDTO updateTeam(TeamUpdateDTO teamUpdateDTO) {
        Team team = teamRepository.findById(teamUpdateDTO.teamId())
                .orElseThrow(() -> new IllegalArgumentException("Team not found"));

        if (teamUpdateDTO.name() != null && !teamUpdateDTO.name().isBlank()) {
            team.setName(teamUpdateDTO.name());
        }

        if (teamUpdateDTO.description() != null && !teamUpdateDTO.description().isBlank()) {
            team.setDescription(teamUpdateDTO.description());
        }

        team.setUpdatedAt();

        Team updatedTeam = teamRepository.save(team);
        return objectMapper.convertValue(updatedTeam, TeamResponseDTO.class);
    }



    @Override
    public void deleteTeam(@TeamId Long teamId) {
        teamRepository.deleteById(teamId);
    }
}
