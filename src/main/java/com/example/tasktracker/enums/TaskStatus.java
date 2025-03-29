package com.example.tasktracker.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TaskStatus {
    NEW("New"),
    IN_PROGRESS("In Progress"),
    REVIEW("Review"),
    COMPLETED("Completed"),
    CLOSED("Closed");

    private final String value;

    public static TaskStatus fromValue(String value) {
        for (TaskStatus status : TaskStatus.values()) {
            if (status.value.equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown value of Task Status: " + value);
    }
}
