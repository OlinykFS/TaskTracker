package com.example.tasktracker.repository;

import com.example.tasktracker.dto.taskDtos.TaskResponseDTO;
import com.example.tasktracker.enums.TaskPriority;
import com.example.tasktracker.enums.TaskStatus;
import com.example.tasktracker.model.Task;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    Optional<TaskResponseDTO> findTaskById(Long id);

    @Query("""
            SELECT
                t.title,
                t.description,
                t.status,
                t.priority,
                u.email,
                t.id
            FROM tasks t
            LEFT JOIN users u ON t.user_id = u.id
            WHERE t.team_id = :teamId
            """)
    Optional<List<TaskResponseDTO>> getAllTasksByTeamId(@Param("teamId") Long teamId);

    @Query("""
    UPDATE tasks
    SET title = :title,
        description = :description,
        status = :status,
        priority = :priority,
        user_id = :userId
    WHERE id = :id
""")
    void updateTaskById(
            @Param("id") Long id,
            @Param("title") String title,
            @Param("description") String description,
            @Param("status") TaskStatus status,
            @Param("priority") TaskPriority priority,
            @Param("userId") Long userId);
}
