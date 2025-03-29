package com.example.tasktracker.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TaskPriority {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High");

    private final String value;

    public static TaskPriority fromValue(String value) {
        for (TaskPriority priority : TaskPriority.values()) {
            if (priority.value.equals(value)) {
                return priority;
            }
        }
        throw new IllegalArgumentException("Unknown value " + value);
    }
}
