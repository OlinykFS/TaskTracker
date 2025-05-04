package com.example.tasktracker.controller;

import com.example.tasktracker.dto.taskDtos.TaskCreateDTO;
import com.example.tasktracker.dto.taskDtos.TaskResponseDTO;
import com.example.tasktracker.dto.taskDtos.TaskUpdateDTO;
import com.example.tasktracker.service.taskService.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teams/{teamId}/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public List<TaskResponseDTO> getTasksByTeamId(@PathVariable Long teamId) {
        return taskService.findAllByTeamId(teamId);
    }

    @GetMapping("/{taskId}")
    public TaskResponseDTO getTaskById(@PathVariable Long teamId, @PathVariable Long taskId) {
        return taskService.findById(teamId, taskId);
    }

    @PostMapping
    public TaskResponseDTO createTask(@PathVariable Long teamId, @Valid @RequestBody TaskCreateDTO dto) {
        return taskService.create(teamId, dto);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTaskById(@PathVariable Long teamId, @PathVariable Long taskId) {
        taskService.delete(teamId, taskId);
    }

    @PatchMapping
    public TaskResponseDTO updateTask(@PathVariable Long teamId, @Valid @RequestBody TaskUpdateDTO dto) {
        return taskService.update(teamId, dto);
    }
}

