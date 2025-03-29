package com.example.tasktracker.exceptions;

public class CustomRoleNotFoundException extends RuntimeException {
    public CustomRoleNotFoundException(String message) {
        super(message);
    }
}
