package com.example.tasktracker.dto.teamMemberDtos;

import com.example.tasktracker.enums.TeamRole;
import com.example.tasktracker.security.TeamId;
import jakarta.validation.constraints.NotNull;

public record TeamMemberCreateDTO(
        @TeamId
        @NotNull(message = "Team must not be null")
        Long teamId,

        @NotNull(message = "User must not be null")
        Long userId,

        @NotNull(message = "Team role must not be null")
        TeamRole teamRole
) {}
