package com.example.tasktracker.exceptions;

public class TeamMemberAlreadyExistException extends RuntimeException {
    public TeamMemberAlreadyExistException(String message) {
        super(message);
    }
}
