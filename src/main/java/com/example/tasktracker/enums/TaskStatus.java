package com.example.tasktracker.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TaskStatus {
    NEW("New"),
    IN_PROGRESS("In Progress"),
    REVIEW("Review"),
    COMPLETED("Completed"),
    CLOSED("Closed");

    private final String value;
}
