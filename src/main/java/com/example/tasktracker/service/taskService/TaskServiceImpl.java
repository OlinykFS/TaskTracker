package com.example.tasktracker.service.taskService;

import com.example.tasktracker.model.Task;
import com.example.tasktracker.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task findTaskById(Long taskId) {
        return taskRepository.findTaskById(taskId);
    }

    @Override
    public void saveTask(Task task) {
        taskRepository.save(task);
    }
}
