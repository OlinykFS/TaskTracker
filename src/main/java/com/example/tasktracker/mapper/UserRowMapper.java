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
                rs.getString("firstname"),
                rs.getString("lastname"),
                UserRole.fromValue(rs.getString("role"))
        );
    }
}
