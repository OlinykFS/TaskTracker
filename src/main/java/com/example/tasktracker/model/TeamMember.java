package com.example.tasktracker.model;


import com.example.tasktracker.enums.TeamRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@Table("team_members")
public class TeamMember {

    @Id
    private Long id;

    private Long userId;
    private Long teamId;

    @Column("team_role")
    private TeamRole teamRole;
}
