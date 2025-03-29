package com.example.tasktracker.service;

import com.example.tasktracker.model.Task;
import com.example.tasktracker.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> findAllTask() {
        return taskRepository.findAll();
    }

    public Task findTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public void saveTask(Task task) {
        taskRepository.save(task);
    }
}
