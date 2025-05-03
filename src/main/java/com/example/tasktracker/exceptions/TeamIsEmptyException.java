package com.example.tasktracker.exceptions;

public class TeamIsEmptyException extends RuntimeException {
    public TeamIsEmptyException(String message) {
        super(message);
    }
}
