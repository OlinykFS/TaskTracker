package com.example.tasktracker.service.taskService;


import com.example.tasktracker.model.Task;

import java.util.List;

public interface TaskService {
    List<Task> findAllTask();
    Task findTaskById(Long id);
    void saveTask(Task task);

}
