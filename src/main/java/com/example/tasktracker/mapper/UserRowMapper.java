package com.example.tasktracker.mapper;

import com.example.tasktracker.enums.UserRole;
import com.example.tasktracker.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(
                rs.getLong("id"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                UserRole.fromValue(rs.getString("role"))
        );
    }
}
