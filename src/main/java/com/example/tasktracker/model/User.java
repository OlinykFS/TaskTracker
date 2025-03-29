package com.example.tasktracker.model;

import com.example.tasktracker.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {

    private Long id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private UserRole role;

}
