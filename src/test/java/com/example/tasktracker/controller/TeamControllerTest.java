package com.example.tasktracker.controller;

import com.example.tasktracker.dto.teamDtos.TeamCreateDTO;
import com.example.tasktracker.dto.teamDtos.TeamResponseDTO;
import com.example.tasktracker.dto.teamDtos.TeamUpdateDTO;
import com.example.tasktracker.exceptions.teamExceptions.TeamNotFoundException;
import com.example.tasktracker.security.jwtUtils.JwtAuthenticationService;
import com.example.tasktracker.security.jwtUtils.JwtTokenProvider;
import com.example.tasktracker.service.teamService.TeamService;
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

@WebMvcTest(TeamController.class)
@AutoConfigureMockMvc(addFilters = false)
public class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private TeamService teamService;

    @MockitoBean
    private JwtTokenProvider jwtTokenProvider;

    @MockitoBean
    private JwtAuthenticationService jwtAuthenticationService;


    @Test
    void shouldCreateTeam() throws Exception {
        TeamCreateDTO createDTO = new TeamCreateDTO("testTeam", "testDescription");
        TeamResponseDTO responseDTO = new TeamResponseDTO(1L,"testTeam", "testDescription");
        when(teamService.createTeam(any(TeamCreateDTO.class)))
                .thenReturn(responseDTO);

        mockMvc.perform(post("/api/v1/teams")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("testTeam"))
                .andExpect(jsonPath("$.description").value("testDescription"));
    }

    @Test
    void createTeam_withInvalidName_shouldReturnBadRequest() throws Exception {
        TeamCreateDTO invalidDto = new TeamCreateDTO("", "Some description");

        mockMvc.perform(post("/api/v1/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void shouldReturnAllTeams() throws Exception {
        TeamResponseDTO responseDTO = new TeamResponseDTO(1L,"testTeam", "testDescription");
        TeamResponseDTO responseDTO1 = new TeamResponseDTO(2L,"testTeam", "testDescription");
        when(teamService.findAllTeams()).thenReturn(List.of(responseDTO, responseDTO1));

        mockMvc.perform(get("/api/v1/teams")
                .content(objectMapper.writeValueAsString(responseDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("testTeam"))
                .andExpect(jsonPath("$[0].description").value("testDescription"))
                .andExpect(jsonPath("$[1].name").value("testTeam"))
                .andExpect(jsonPath("$[1].description").value("testDescription"));
    }

    @Test
    void returnAllTeams_ShouldReturnTeamNotFound() throws Exception {
        when(teamService.findAllTeams()).thenThrow(new TeamNotFoundException("Teams not found"));

        mockMvc.perform(get("/api/v1/teams"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Teams not found"));
    }

    @Test
    void shouldReturnTeamById() throws Exception {
        TeamResponseDTO responseDTO = new TeamResponseDTO(1L,"testTeam", "testDescription");
        when(teamService.findTeamById(1L)).thenReturn(responseDTO);
        mockMvc.perform(get("/api/v1/teams/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("testTeam"))
                .andExpect(jsonPath("$.description").value("testDescription"));
    }

    @Test
    void getTeamById_ShouldReturnTeamNotFound() throws Exception {
        when(teamService.findTeamById(1L)).thenThrow(new TeamNotFoundException("Team not found"));
        mockMvc.perform(get("/api/v1/teams/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Team not found"));
    }

    @Test
    void shouldUpdateTeam() throws Exception {
        TeamResponseDTO responseDTO = new TeamResponseDTO(1L,"testTeam", "testDescription");
        TeamUpdateDTO updateDTO = new TeamUpdateDTO(1L,"testTeam", "testDescription");

        when(teamService.updateTeam(updateDTO)).thenReturn(responseDTO);
        mockMvc.perform(put("/api/v1/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("testTeam"))
                .andExpect(jsonPath("$.description").value("testDescription"));
    }
    @Test
    void shouldReturnBadRequest_whenInvalidUpdateRequest() throws Exception {
        TeamUpdateDTO invalidUpdateDTO = new TeamUpdateDTO(1L, "", "testDescription");

        mockMvc.perform(put("/api/v1/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidUpdateDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Validation failed"))
                .andExpect(jsonPath("$.details.name").value("Team name must not be blank"));  // Проверка конкретной ошибки для поля name
    }

    @Test
    void deleteTask() throws Exception {
        mockMvc.perform(delete("/api/v1/teams/2"))
                .andExpect(status().isNoContent());
    }
}
