package com.example.tasktracker.dto.teamMemberDtos;

import com.example.tasktracker.enums.TeamRole;
import com.example.tasktracker.security.TeamId;

public record TeamMemberCreateDTO(Long userId, @TeamId Long teamId, TeamRole teamRole) {
}
