package com.example.tasktracker.controller;

import com.example.tasktracker.dto.teamMemberDtos.TeamMemberCreateDTO;
import com.example.tasktracker.dto.teamMemberDtos.TeamMemberResponseDTO;
import com.example.tasktracker.enums.TeamRole;
import com.example.tasktracker.security.jwtUtils.JwtAuthenticationService;
import com.example.tasktracker.security.jwtUtils.JwtTokenProvider;
import com.example.tasktracker.service.teamMembersService.TeamMemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;

@WebMvcTest(TeamController.class)
@AutoConfigureMockMvc(addFilters = false)
public class TeamMemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private TeamMemberService teamMemberService;

    @MockitoBean
    private JwtTokenProvider jwtTokenProvider;

    @MockitoBean
    private JwtAuthenticationService jwtAuthenticationService;


    @Test
    void shouldAddMemberToTeam() throws Exception {
        TeamMemberCreateDTO createDTO = new TeamMemberCreateDTO(1L, 1L, TeamRole.ROLE_MEMBER);
        TeamMemberResponseDTO teamMemberResponseDTO = new TeamMemberResponseDTO("test@test.com", "test", "test", "");
        when(teamMemberService.addTeamMember(createDTO)).thenReturn()
    }

}
