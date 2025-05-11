package com.example.tasktracker.repository;

import com.example.tasktracker.dto.teamDtos.TeamResponseDTO;
import com.example.tasktracker.model.Team;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {
    @Query("""
SELECT t.id, t.name, t.description  FROM teams t
""")
    Optional<List<TeamResponseDTO>> getAllTeams();

    TeamResponseDTO getTeamById(Long id);

    @Query("""
    UPDATE teams
    SET name = :name,
        description = :description
    WHERE id = :teamId
""")
    void updateTeamById(Long teamId, String name, String description);

}
