package com.example.tasktracker.repository;

import com.example.tasktracker.enums.TaskStatus;
import com.example.tasktracker.mapper.TaskRowMapper;
import com.example.tasktracker.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TaskRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<Task> findAll() {
        String sql = "SELECT * FROM tasks";
        return jdbcTemplate.query(sql, new TaskRowMapper());
    }

    public Task findById(Long id) {
        String sql = "SELECT * FROM tasks WHERE id = ?";
        return jdbcTemplate.query(sql, new TaskRowMapper(), id).get(0);
    }

    public Task findByTitle(String title) {
        String sql = "SELECT * FROM tasks WHERE title = ?";
        return jdbcTemplate.query(sql, new TaskRowMapper(), title).get(0);
    }

    public List<Task> findByStatus(TaskStatus status) {
        String sql = "SELECT * FROM tasks WHERE status = ?";
        return jdbcTemplate.query(sql, new TaskRowMapper(), status);
    }

    public void save(Task task) {
        String sql = "INSERT INTO tasks (title, status) VALUES (?, ?)";
        jdbcTemplate.update(sql, task.getTitle(), task.getStatus().name());
    }

}
