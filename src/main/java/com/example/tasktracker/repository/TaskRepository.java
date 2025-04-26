package com.example.tasktracker.repository;

import com.example.tasktracker.enums.TaskStatus;
import com.example.tasktracker.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long > {

    List<Task> findAll();

    List<Task> findByStatus(TaskStatus status);
    Task findByTitle(String title);

    Task findTaskById(Long id);
}
