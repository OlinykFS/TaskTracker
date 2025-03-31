package com.example.tasktracker.controller;


import com.example.tasktracker.model.Task;
import com.example.tasktracker.service.taskService.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/all")
    public List<Task> getTask() {
        return taskService.findAllTask();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.findTaskById(id);
    }

    @PostMapping("/new")
    public void createTask(@RequestBody Task task) {
        taskService.saveTask(task);

    }


}
