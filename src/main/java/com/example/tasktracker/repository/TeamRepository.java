package com.example.tasktracker.repository;

import com.example.tasktracker.mapper.TeamRowMapper;
import com.example.tasktracker.model.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TeamRepository {

    private final JdbcTemplate jdbcTemplate;


    public Team getTeamById(Long teamId) {
        String sql = "SELECT * FROM teams WHERE id = ?";
        return jdbcTemplate.query(sql, new TeamRowMapper(), teamId).get(0);
    }

    public int createTeam(Team team) {
        String sql = "INSERT INTO teams (name, description, owner_id) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, team.getName(), team.getDescription(), team.getOwnerId());
    }

    public Team getTeamByName(String name) {
        String sql = "SELECT * FROM teams WHERE name = ?";
        return jdbcTemplate.query(sql, new TeamRowMapper(), name).get(0);
    }

    public int updateTeam(Team team) {
        String sql = "UPDATE teams SET name = ?, description = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        return jdbcTemplate.update(sql, team.getName(), team.getDescription(), team.getId());
    }
}
