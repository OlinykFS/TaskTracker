package com.example.tasktracker.dto.teamMemberDtos;

import com.example.tasktracker.enums.TeamRole;

public record TeamMemberCreateDTO(Long userId, Long teamId, TeamRole teamRole) {
}
