package com.example.tasktracker.controller;

import com.example.tasktracker.dto.taskDtos.TaskCreateDTO;
import com.example.tasktracker.dto.taskDtos.TaskResponseDTO;
import com.example.tasktracker.dto.taskDtos.TaskUpdateDTO;
import com.example.tasktracker.service.taskService.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<TaskResponseDTO> createTask(@PathVariable Long teamId, @Valid @RequestBody TaskCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.create(teamId, dto));
    }


    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long teamId, @PathVariable Long taskId) {
        taskService.delete(teamId, taskId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    public TaskResponseDTO updateTask(@PathVariable Long teamId, @Valid @RequestBody TaskUpdateDTO dto) {
        return taskService.update(teamId, dto);
    }
}

