package com.example.tasktracker.dto;

import java.util.Map;

public record ErrorResponse(String message, String status, int code, Map<String, String> details) {
    public ErrorResponse(String message, String status, int code) {
        this(message, status, code, null);
    }
}

