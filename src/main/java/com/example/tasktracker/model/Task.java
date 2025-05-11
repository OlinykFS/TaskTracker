package com.example.tasktracker.model;

import com.example.tasktracker.enums.TaskPriority;
import com.example.tasktracker.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Table("tasks")
public class Task {

    @Id
    private Long id;
    private String title;

    private String description;

    private TaskStatus status;

    private TaskPriority priority;

    private Long teamId;

    private Long userId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
