package com.example.tasktracker.model;

import com.example.tasktracker.enums.TaskPriority;
import com.example.tasktracker.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Task {

    private Long id;

    private String title;

    private String description;

    private TaskStatus status;

    private TaskPriority priority;

    private Long teamId;
    private Long userId;
}
