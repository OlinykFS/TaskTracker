package com.example.tasktracker.service.teamMembersService;

import com.example.tasktracker.dto.teamMemberDtos.TeamMemberCreateDTO;
import com.example.tasktracker.dto.teamMemberDtos.TeamMemberResponseDTO;
import com.example.tasktracker.enums.TeamRole;
import com.example.tasktracker.exceptions.teamExceptions.TeamEmptyException;
import com.example.tasktracker.exceptions.teamExceptions.TeamMemberAlreadyExistException;
import com.example.tasktracker.exceptions.teamExceptions.TeamMemberNotFoundException;
import com.example.tasktracker.mapper.TeamMemberMapper;
import com.example.tasktracker.model.TeamMember;
import com.example.tasktracker.repository.TeamMemberRepository;
import com.example.tasktracker.security.teamAccessUtils.TeamAccess;
import com.example.tasktracker.security.teamAccessUtils.TeamId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamMemberServiceImpl implements TeamMemberService {

    private final TeamMemberRepository teamMemberRepository;
    private final TeamMemberMapper teamMemberMapper;

    @Override
    @TeamAccess(requiredRoles = {TeamRole.ROLE_MANAGER})
    public List<TeamMemberResponseDTO> findAllTeamMembersByTeamId(@TeamId Long teamId) {
        return teamMemberRepository.getTeamMembersWithUserDetailsByTeamId(teamId)
                .orElseThrow(() -> new TeamEmptyException("the Team is Empty"));
    }

    @Override
    @TeamAccess(requiredRoles = {TeamRole.ROLE_MANAGER})
    @Transactional
    public TeamMemberResponseDTO addTeamMember(TeamMemberCreateDTO dto) {
        try {
            teamMemberRepository.save(teamMemberMapper.toEntity(dto));
        } catch (DuplicateKeyException | DbActionExecutionException e) {
            throw new TeamMemberAlreadyExistException("Team member already exists");
        }
        return teamMemberRepository.getTeamMemberWithUserDetailsByUserId(dto.teamId(), dto.userId())
                .orElseThrow(() -> new TeamMemberNotFoundException("Team member not found"));
    }

    @Override
    @TeamAccess(requiredRoles = {TeamRole.ROLE_MANAGER})
    @Transactional
    public void deleteTeamMember(@TeamId Long teamId, Long userId) {
        TeamMember tm = teamMemberRepository.getTeamMemberByTeamIdAndUserId(teamId, userId)
                .orElseThrow(() -> new UsernameNotFoundException("Team member not found"));
        teamMemberRepository.deleteById(tm.getId());
    }

    @Override
    public boolean isUserMemberOfTeam(Long teamId, Long userId) {
        return teamMemberRepository.existsByTeamIdAndUserId(teamId, userId);
    }

}
