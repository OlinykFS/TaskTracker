package com.example.tasktracker.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
