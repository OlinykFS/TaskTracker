package com.example.tasktracker.service.taskService;


import com.example.tasktracker.dto.taskDtos.TaskCreateDTO;
import com.example.tasktracker.dto.taskDtos.TaskResponseDTO;
import com.example.tasktracker.dto.taskDtos.TaskUpdateDTO;

import java.util.List;

public interface TaskService {
    List<TaskResponseDTO> findAllByTeamId(Long teamId);
    TaskResponseDTO findById(Long taskId);
    TaskResponseDTO create(TaskCreateDTO dto);
    void delete(Long taskId);
    TaskResponseDTO update(TaskUpdateDTO dto);
}
