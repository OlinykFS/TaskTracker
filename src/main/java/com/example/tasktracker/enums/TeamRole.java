package com.example.tasktracker.enums;

import com.example.tasktracker.exceptions.CustomRoleNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TeamRole {
    ROLE_MEMBER("Member"),
    ROLE_MANAGER("Manager"),
    ROLE_TEAM_ADMIN("Team Admin"),
    ROLE_MODERATOR("Moderator"),
    ROLE_LEAD("Lead"),
    ROLE_OBSERVER("Observer");

    private final String value;

    public static TeamRole fromValue(String value) {
        try {
            return TeamRole.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new CustomRoleNotFoundException("Unknown TeamRole value: " + value);
        }
    }


}
