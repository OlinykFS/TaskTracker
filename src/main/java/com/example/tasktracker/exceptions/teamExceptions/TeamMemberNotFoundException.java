package com.example.tasktracker.exceptions.teamExceptions;

public class TeamMemberNotFoundException extends RuntimeException {
    public TeamMemberNotFoundException(String message) {
        super(message);
    }
}
