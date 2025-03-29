package com.example.tasktracker.repository;

import com.example.tasktracker.mapper.TeamMemberRowMapper;
import com.example.tasktracker.model.TeamMember;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TeamMemberRepository {

    private final JdbcTemplate jdbcTemplate;

    public int addUserToTeam(Long userId, Long teamId, String teamRole) {
        String sql = "INSERT INTO team_members (user_id, team_id, team_role) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, userId, teamId, teamRole);
    }

    public List<TeamMember> getTeamMembersByTeamId(Long teamId) {
        String sql = "select * from team_members where team_id = ?";
        return jdbcTemplate.query(sql, new TeamMemberRowMapper(), teamId);
    }

    public List<TeamMember> findRolesByUserId(Long userId) {
        String sql = "SELECT team_id, team_role FROM team_members WHERE user_id = ?";
        return jdbcTemplate.query(sql, new TeamMemberRowMapper(), userId);
    }

}
