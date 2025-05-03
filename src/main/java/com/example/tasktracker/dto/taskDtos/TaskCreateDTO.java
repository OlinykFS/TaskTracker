package com.example.tasktracker.dto.taskDtos;

import com.example.tasktracker.enums.TaskPriority;
import com.example.tasktracker.enums.TaskStatus;
import com.example.tasktracker.security.TeamId;

public record TaskCreateDTO (

        String title,

        String description,

        TaskStatus status,

        TaskPriority priority,

        @TeamId
        Long teamId
) {}
