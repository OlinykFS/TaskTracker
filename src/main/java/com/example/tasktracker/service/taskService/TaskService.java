package com.example.tasktracker.service.taskService;


import com.example.tasktracker.dto.taskDtos.TaskCreateDTO;
import com.example.tasktracker.dto.taskDtos.TaskResponseDTO;
import com.example.tasktracker.dto.taskDtos.TaskUpdateDTO;

import java.util.List;

public interface TaskService {
    List<TaskResponseDTO> findAllByTeamId(Long teamId);
    TaskResponseDTO findById(Long teamId, Long taskId);
    TaskResponseDTO create(Long teamId, TaskCreateDTO dto);
    void delete(Long teamId, Long taskId);
    TaskResponseDTO update(Long teamId, TaskUpdateDTO dto);
}
