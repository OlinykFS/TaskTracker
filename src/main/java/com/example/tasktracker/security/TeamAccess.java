package com.example.tasktracker.security;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TeamAccess {
    String requiredRole();
}
