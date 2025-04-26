package com.example.tasktracker.service.taskService;


import com.example.tasktracker.model.Task;

import java.util.List;

public interface TaskService {
    List<Task> findAllTasks();
    Task findTaskById(Long taskId);
    void saveTask(Task task);

}
