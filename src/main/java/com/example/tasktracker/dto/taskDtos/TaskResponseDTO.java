package com.example.tasktracker.dto.taskDtos;

public record TaskResponseDTO(
        String title,
        String description,
        String status,
        String priority,
        String email,
        Long id,
        String createdAt,
        String updatedAt
) {}

