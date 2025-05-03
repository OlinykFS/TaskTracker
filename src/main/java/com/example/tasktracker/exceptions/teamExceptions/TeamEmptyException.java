package com.example.tasktracker.exceptions.teamExceptions;

public class TeamEmptyException extends RuntimeException {
    public TeamEmptyException(String message) {
        super(message);
    }
}
