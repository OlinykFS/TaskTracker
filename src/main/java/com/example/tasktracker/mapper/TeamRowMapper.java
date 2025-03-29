package com.example.tasktracker.mapper;

import com.example.tasktracker.model.Team;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class TeamRowMapper implements RowMapper<Team> {

    @Override
    public Team mapRow(ResultSet rs, int numRow) throws SQLException {
        return new Team(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getLong("owner_id"),
                rs.getObject("created_at", LocalDateTime.class),
                rs.getObject("updated_at", LocalDateTime.class)
        );
    }
}
