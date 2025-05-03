package com.example.tasktracker.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum TeamRole {
    ROLE_MEMBER("Member"),
    ROLE_MANAGER("Manager"),
    ROLE_TEAM_ADMIN("Team Admin"),
    ROLE_MODERATOR("Moderator"),
    ROLE_LEAD("Lead"),
    ROLE_OBSERVER("Observer");

    private final String value;
}

