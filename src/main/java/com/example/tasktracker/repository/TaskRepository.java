package com.example.tasktracker.repository;

import com.example.tasktracker.dto.taskDtos.TaskResponseDTO;
import com.example.tasktracker.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {


    Optional<TaskResponseDTO> findTaskById(Long id);

    Optional<List<TaskResponseDTO>> getAllTasksByTeamId(Long teamId);
}
