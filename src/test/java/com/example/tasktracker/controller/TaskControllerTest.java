package com.example.tasktracker.controller;

import com.example.tasktracker.dto.taskDtos.TaskCreateDTO;
import com.example.tasktracker.dto.taskDtos.TaskResponseDTO;
import com.example.tasktracker.dto.taskDtos.TaskUpdateDTO;
import com.example.tasktracker.enums.TaskPriority;
import com.example.tasktracker.enums.TaskStatus;
import com.example.tasktracker.exceptions.taskExceptions.TaskNotFoundException;
import com.example.tasktracker.security.jwtUtils.JwtAuthenticationService;
import com.example.tasktracker.security.jwtUtils.JwtTokenProvider;
import com.example.tasktracker.service.taskService.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TaskController.class)
@AutoConfigureMockMvc(addFilters = false)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JwtTokenProvider jwtTokenProvider;

    @MockitoBean
    private JwtAuthenticationService jwtAuthenticationService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private TaskService taskService;

    @Test
    void getTasksByTeamId_shouldReturnListOfTasks() throws Exception {
        TaskResponseDTO responseDTO1 = new TaskResponseDTO("taskTitle1", "taskDescription1", "New", "High", "test1@test.com", 1L, "2025-05-11 13:30:49", "2025-05-11 13:30:49");
        TaskResponseDTO responseDTO2 = new TaskResponseDTO("taskTitle2", "taskDescription2", "InProgress", "Low", "test2@test.com", 2L, "2025-05-11 13:30:49", "2025-05-11 13:30:49");

        when(taskService.findAllByTeamId(1L)).thenReturn(List.of(responseDTO1, responseDTO2));

        mockMvc.perform(get("/api/v1/teams/1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].title").value("taskTitle1"))
                .andExpect(jsonPath("$[0].description").value("taskDescription1"))
                .andExpect(jsonPath("$[0].status").value("New"))
                .andExpect(jsonPath("$[0].priority").value("High"))
                .andExpect(jsonPath("$[0].email").value("test1@test.com"))
                .andExpect(jsonPath("$[1].title").value("taskTitle2"));
    }
    @Test
    void getTaskById_shouldReturnTask() throws Exception {
        TaskResponseDTO responseDTO1 = new TaskResponseDTO("taskTitle1", "taskDescription1", "New", "High", "test1@test.com", 1L, "2025-05-11 13:30:49", "2025-05-11 13:30:49");
        when(taskService.findById(any(Long.class), any(Long.class))).thenReturn(responseDTO1);
        mockMvc.perform(get("/api/v1/teams/1/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(responseDTO1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("taskTitle1"));
    }

    @Test
    void getTaskById_shouldReturnNotFound() throws Exception {
        when(taskService.findById(any(Long.class), any(Long.class)))
                .thenThrow(new TaskNotFoundException("Task not found"));
        mockMvc.perform(get("/api/v1/teams/1/tasks/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Task not found"));
    }

    @Test
    void createTask_shouldReturnCreatedTask() throws Exception {
        TaskCreateDTO createDTO = new TaskCreateDTO("taskTitle1", "taskDescription1", TaskStatus.NEW, TaskPriority.HIGH, 1L);

        TaskResponseDTO responseDTO = new TaskResponseDTO("taskTitle1", "taskDescription1", "New", "High", "test1@test.com", 1L, "2025-05-11 13:30:49", "2025-05-11 13:30:49");

        when(taskService.create(any(Long.class), any(TaskCreateDTO.class)))
                .thenReturn(responseDTO);

        mockMvc.perform(post("/api/v1/teams/1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("taskTitle1"))
                .andExpect(jsonPath("$.description").value("taskDescription1"))
                .andExpect(jsonPath("$.priority").value("High"))
                .andExpect(jsonPath("$.email").value("test1@test.com"))
                .andExpect(jsonPath("$.status").value("New"));
    }

    @Test
    void deleteTask() throws Exception {
        mockMvc.perform(delete("/api/v1/teams/1/tasks/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void updateTask_shouldReturnUpdatedTask() throws Exception {
        TaskUpdateDTO updateDTO = new TaskUpdateDTO("Updated Title", "Updated Description", TaskStatus.COMPLETED, TaskPriority.HIGH, 1L, 1L);
        TaskResponseDTO expectedResponse = new TaskResponseDTO("Updated Title", "Updated Description", "Completed", "High", "test1@test.com", 1L, "2025-05-11 13:30:49", "2025-05-11 14:30:49");

        when(taskService.update(any(Long.class), any(TaskUpdateDTO.class)))
                .thenReturn(expectedResponse);

        mockMvc.perform(patch("/api/v1/teams/1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"))
                .andExpect(jsonPath("$.description").value("Updated Description"))
                .andExpect(jsonPath("$.status").value("Completed"))
                .andExpect(jsonPath("$.priority").value("High"))
                .andExpect(jsonPath("$.email").value("test1@test.com"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.createdAt").value("2025-05-11 13:30:49"))
                .andExpect(jsonPath("$.updatedAt").value("2025-05-11 14:30:49"));
    }
    @Test
    void updateTask_shouldReturnNotFoundIfTaskDoesNotExist() throws Exception {
        TaskUpdateDTO updateDTO = new TaskUpdateDTO("Task", "Doesnt matter", TaskStatus.COMPLETED, TaskPriority.HIGH, 1L, 999L);

        when(taskService.update(any(Long.class), any(TaskUpdateDTO.class)))
                .thenThrow(new TaskNotFoundException("Task not found"));

        mockMvc.perform(patch("/api/v1/teams/1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Task not found"))
                .andExpect(jsonPath("$.code").value(404));
    }




}
