package com.example.tasktracker.dto.teamMemberDtos;

import com.example.tasktracker.enums.TeamRole;

public record TeamMemberResponseDTO(String email, String firstname, String lastname, TeamRole teamRole) {

}
