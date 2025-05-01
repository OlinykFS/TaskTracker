package com.example.tasktracker.repository;

import com.example.tasktracker.dto.teamMemberDtos.TeamMemberResponseDTO;
import com.example.tasktracker.model.TeamMember;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamMemberRepository extends CrudRepository<TeamMember, Long> {
    @Query("""
    SELECT
        u.email,
        u.firstname,
        u.lastname,
        tm.team_role
    FROM team_members tm
    JOIN users u ON tm.user_id = u.id
    WHERE tm.team_id = :teamId
""")
    List<TeamMemberResponseDTO> getTeamMembersWithUserDetailsByTeamId(Long teamId);



    List<TeamMember> getTeamMembersByUserId(Long userId);

    List<TeamMember> findAllByUserId(Long userId);
}
