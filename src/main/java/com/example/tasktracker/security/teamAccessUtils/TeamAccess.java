package com.example.tasktracker.security.teamAccessUtils;

import com.example.tasktracker.enums.TeamRole;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TeamAccess {
    TeamRole[] requiredRoles();
}
