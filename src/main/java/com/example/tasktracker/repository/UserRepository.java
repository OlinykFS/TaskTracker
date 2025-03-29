package com.example.tasktracker.repository;

import com.example.tasktracker.mapper.UserRowMapper;
import com.example.tasktracker.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public User findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        return jdbcTemplate.query(sql, new UserRowMapper(), email).get(0);
    }

    public int createUser(User user) {
        String sql = "INSERT INTO users (email, password, firstname, lastname) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName());
    }
}
