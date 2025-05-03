package com.example.tasktracker.dto.taskDtos;

import com.example.tasktracker.enums.TaskPriority;
import com.example.tasktracker.enums.TaskStatus;

public record TaskUpdateDTO (
        String title,
        String description,
        TaskStatus status,
        TaskPriority priority,
        Long userId,
        Long taskId
) {}
