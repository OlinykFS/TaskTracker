package com.example.tasktracker.dto.teamDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TeamCreateDTO(

        @NotBlank(message = "Team name must not be blank")
        @Size(max = 100, message = "Team name must not exceed 100 characters")
        String name,

        @NotBlank(message = "Description must not be blank")
        @Size(max = 255, message = "Description must not exceed 255 characters")
        String description

) {}
