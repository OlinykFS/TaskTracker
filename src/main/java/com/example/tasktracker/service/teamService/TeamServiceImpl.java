package com.example.tasktracker.service.teamService;

import com.example.tasktracker.dto.teamDtos.TeamCreateDTO;
import com.example.tasktracker.dto.teamDtos.TeamResponseDTO;
import com.example.tasktracker.dto.teamDtos.TeamUpdateDTO;
import com.example.tasktracker.enums.TeamRole;
import com.example.tasktracker.exceptions.teamExceptions.TeamNotFoundException;
import com.example.tasktracker.mapper.TeamMapper;
import com.example.tasktracker.model.Team;
import com.example.tasktracker.repository.TeamRepository;
import com.example.tasktracker.security.teamAccessUtils.TeamAccess;
import com.example.tasktracker.security.teamAccessUtils.TeamId;
import com.example.tasktracker.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    private final UserService userService;

    @Override
    @Transactional
    public TeamResponseDTO createTeam(TeamCreateDTO teamCreateDTO) {
        Team team = teamMapper.toEntity(teamCreateDTO);
        team.setOwnerId(userService.getCurrentUserId());
        teamRepository.save(team);
        return teamMapper.toDto(team);
    }

    @Override
    public List<TeamResponseDTO> findAllTeams() {
        return teamRepository.getAllTeams()
                .orElseThrow(() -> new TeamNotFoundException("Teams not Found"));

    }

    @Override
    public TeamResponseDTO findTeamById(@TeamId Long teamId) {
        return teamRepository.getTeamById(teamId);
    }

    @Override
    @Transactional
    public TeamResponseDTO updateTeam(TeamUpdateDTO teamUpdateDTO) {
        Team team = teamRepository.findById(teamUpdateDTO.teamId())
                .orElseThrow(() -> new TeamNotFoundException("Team not found"));
        teamMapper.updateEntityFromDto(teamUpdateDTO, team);
        teamRepository.updateTeamById(team.getId(), team.getName(), team.getDescription());
        return teamMapper.toDto(team);
    }

    @Override
    @TeamAccess(requiredRoles = {TeamRole.ROLE_TEAM_ADMIN})
    public void deleteTeam(@TeamId Long teamId) {
        teamRepository.deleteById(teamId);
    }
}
