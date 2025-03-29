package com.example.tasktracker.mapper;

import com.example.tasktracker.enums.TaskPriority;
import com.example.tasktracker.enums.TaskStatus;
import com.example.tasktracker.model.Task;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskRowMapper implements RowMapper<Task> {

    @Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Task(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("description"),
                TaskStatus.fromValue(rs.getString("status")),
                TaskPriority.fromValue(rs.getString("priority")),
                rs.getLong("teamId"),
                rs.getLong("userId")
        );
    }
}
