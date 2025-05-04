package com.example.tasktracker.dto.taskDtos;

import com.example.tasktracker.enums.TaskPriority;
import com.example.tasktracker.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskCreateDTO (
        @NotBlank(message = "Task title must not be blank")
        String title,

        @NotBlank(message = "Task description must not be blank")
        String description,

        @NotNull(message = "Task status must not be null")
        TaskStatus status,

        @NotNull(message = "Task priority must not be null")
        TaskPriority priority,

        Long userId
) {}
