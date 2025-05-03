package com.example.tasktracker.service.taskService;

import com.example.tasktracker.dto.taskDtos.TaskCreateDTO;
import com.example.tasktracker.dto.taskDtos.TaskResponseDTO;
import com.example.tasktracker.dto.taskDtos.TaskUpdateDTO;
import com.example.tasktracker.exceptions.taskExceptions.TaskNotFoundException;
import com.example.tasktracker.mapper.TaskMapper;
import com.example.tasktracker.model.Task;
import com.example.tasktracker.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public List<TaskResponseDTO> findAllByTeamId(Long teamId) {
        return taskRepository.getAllTasksByTeamId(teamId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
    }

    @Override
    public TaskResponseDTO findById(Long taskId) {
        return taskRepository.findTaskById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
    }

    @Override
    @Transactional
    public TaskResponseDTO create(TaskCreateDTO taskCreateDTO) {
        Task task = taskMapper.toEntity(taskCreateDTO);
        taskRepository.save(task);
        return taskMapper.toDto(task);
    }

    @Override
    public void delete(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Transactional
    public TaskResponseDTO update(TaskUpdateDTO taskUpdateDTO) {
        Task task = taskRepository.findById(taskUpdateDTO.taskId())
                .orElseThrow(() -> new RuntimeException("Task not found"));
        taskMapper.updateEntityFromDto(taskUpdateDTO, task);
        taskRepository.save(task);
        return taskMapper.toDto(task);
    }
}
