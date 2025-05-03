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
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/{teamId}")
    public List<TaskResponseDTO> getTasksByTeamId(@PathVariable Long teamId) {
        return taskService.findAllByTeamId(teamId);
    }

    @GetMapping("/{taskId}")
    public TaskResponseDTO getTaskById(@PathVariable Long taskId) {
        return taskService.findById(taskId);
    }

    @PostMapping
    public TaskResponseDTO createTask(@RequestBody TaskCreateDTO dto) {
        return taskService.create(dto);
    }
    @DeleteMapping
    public void deleteTaskById(@RequestParam Long taskId) {
        taskService.delete(taskId);
    }

    @PatchMapping
    public TaskResponseDTO updateTask(@Valid @RequestBody TaskUpdateDTO dto) {
        return taskService.update(dto);
    }
}

