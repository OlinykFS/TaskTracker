package com.example.tasktracker.dto.taskDtos;

import com.example.tasktracker.enums.TaskPriority;
import com.example.tasktracker.enums.TaskStatus;

public record TaskResponseDTO (

        String title,

        String description,

        TaskStatus status,

        TaskPriority priority,

        String email
) {}
