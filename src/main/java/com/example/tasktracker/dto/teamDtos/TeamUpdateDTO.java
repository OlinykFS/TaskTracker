package com.example.tasktracker.dto.teamDtos;

import com.example.tasktracker.security.teamAccessUtils.TeamId;
import jakarta.validation.constraints.Size;

public record TeamUpdateDTO(

        @TeamId
        Long teamId,

        @Size(min = 1, max = 100, message = "Team name must not be blank")
        String name,

        @Size(max = 255, message = "Description can't be longer than 255 characters")
        String description

) {}
