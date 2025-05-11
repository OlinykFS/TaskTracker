package com.example.tasktracker.repository;

import com.example.tasktracker.dto.taskDtos.TaskResponseDTO;
import com.example.tasktracker.model.Task;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    @Query("""
            SELECT
                t.title,
                t.description,
                t.status,
                t.priority,
                u.email,
                t.id,
                t.created_at,
                t.updated_at
            FROM tasks t
            LEFT JOIN users u ON t.user_id = u.id
            WHERE t.id = :id
            """)
    Optional<TaskResponseDTO> findTaskById(Long id);

    @Query("""
            SELECT
                t.title,
                t.description,
                t.status,
                t.priority,
                u.email,
                t.id,
                t.created_at,
                t.updated_at
            FROM tasks t
            LEFT JOIN users u ON t.user_id = u.id
            WHERE t.team_id = :teamId
            """)
    Optional<List<TaskResponseDTO>> getAllTasksByTeamId(@Param("teamId") Long teamId);
}
