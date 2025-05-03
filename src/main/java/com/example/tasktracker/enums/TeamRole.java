package com.example.tasktracker.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum TeamRole {
    ROLE_OBSERVER("Observer", 0),
    ROLE_MEMBER("Member", 1),
    ROLE_MODERATOR("Moderator", 2),
    ROLE_MANAGER("Manager", 3),
    ROLE_LEAD("Lead", 4),
    ROLE_TEAM_ADMIN("Team Admin", 5);

    private final String value;
    private final int level;

    public boolean hasAccessTo(TeamRole required) {
        return this.level >= required.level;
    }
}


