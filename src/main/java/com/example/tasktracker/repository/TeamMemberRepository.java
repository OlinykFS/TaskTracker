package com.example.tasktracker.repository;

import com.example.tasktracker.dto.teamMemberDtos.TeamMemberResponseDTO;
import com.example.tasktracker.model.TeamMember;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    Optional<List<TeamMemberResponseDTO>> getTeamMembersWithUserDetailsByTeamId(@Param("teamId") Long teamId);

    @Query("""
        SELECT
            u.email,
            u.firstname,
            u.lastname,
            tm.team_role
        FROM team_members tm
        JOIN users u ON tm.user_id = u.id
        WHERE tm.team_id = :teamId AND tm.user_id = :userId
    """)
    Optional<TeamMemberResponseDTO> getTeamMemberWithUserDetailsByUserId(@Param("teamId") Long teamId, @Param("userId") Long userId);

    List<TeamMember> findAllByUserId(Long userId);

    Optional<TeamMember> getTeamMemberByTeamIdAndUserId(Long teamId, Long userId);
}
