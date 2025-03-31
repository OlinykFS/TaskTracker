package com.example.tasktracker.model;


import com.example.tasktracker.enums.TeamRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TeamMember {
    private Long userId;
    private Long teamId;
    private TeamRole teamRole;
}
