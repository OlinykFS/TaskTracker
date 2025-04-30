package com.example.tasktracker.model;

import com.example.tasktracker.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@Table("users")
public class User {

    @Id
    private Long id;

    private String email;

    private String password;

    @Column("firstname")
    private String firstName;

    @Column("lastname")
    private String lastName;

    private UserRole role;



}
