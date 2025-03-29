package com.example.tasktracker.mapper;

import com.example.tasktracker.enums.TeamRole;
import com.example.tasktracker.model.TeamMember;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeamMemberRowMapper implements RowMapper<TeamMember> {

    @Override
    public TeamMember mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TeamMember(
                rs.getLong("userId"),
                rs.getLong("teamId"),
                TeamRole.fromValue(rs.getString("team_role"))
        );
    }
}
