package com.example.tasktracker.enums;

import com.example.tasktracker.exceptions.CustomRoleNotFoundException;
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

    public static UserRole fromValue(String value) {
        for (UserRole role : values()) {
            if (role.getValue().equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new CustomRoleNotFoundException("Unknown UserRole value: " + value);
    }
}
