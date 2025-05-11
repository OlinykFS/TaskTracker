package com.example.tasktracker.controller;

import com.example.tasktracker.dto.taskDtos.TaskResponseDTO;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    }
}

