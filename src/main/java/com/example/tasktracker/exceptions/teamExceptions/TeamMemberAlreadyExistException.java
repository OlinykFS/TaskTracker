package com.example.tasktracker.exceptions.teamExceptions;

public class TeamMemberAlreadyExistException extends RuntimeException {
    public TeamMemberAlreadyExistException(String message) {
        super(message);
    }
}
