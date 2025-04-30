package com.example.tasktracker.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    ROLE_USER("User"),
    ROLE_MODERATOR("Moderator"),
    ROLE_ADMIN("Admin");

    private final String value;

    UserRole(String value) {
        this.value = value;
    }
}
