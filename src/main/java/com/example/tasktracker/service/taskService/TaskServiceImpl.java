package com.example.tasktracker.service.taskService;

import com.example.tasktracker.dto.taskDtos.TaskCreateDTO;
import com.example.tasktracker.dto.taskDtos.TaskResponseDTO;
import com.example.tasktracker.dto.taskDtos.TaskUpdateDTO;
import com.example.tasktracker.enums.TeamRole;
import com.example.tasktracker.exceptions.taskExceptions.TaskNotFoundException;
import com.example.tasktracker.exceptions.teamExceptions.TeamMemberNotFoundException;
import com.example.tasktracker.mapper.TaskMapper;
import com.example.tasktracker.model.Task;
import com.example.tasktracker.repository.TaskRepository;
import com.example.tasktracker.security.teamAccessUtils.TeamAccess;
import com.example.tasktracker.security.teamAccessUtils.TeamId;
import com.example.tasktracker.service.teamMembersService.TeamMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final TeamMemberService teamMemberService;
    private final JdbcAggregateTemplate jdbcAggregateTemplate;

    @Override
    public List<TaskResponseDTO> findAllByTeamId(Long teamId) {
        return taskRepository.getAllTasksByTeamId(teamId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
    }

    @Override
    public TaskResponseDTO findById(@TeamId Long teamId, Long taskId) {
        return taskRepository.findTaskById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
    }

    @Override
    @Transactional
    @TeamAccess(requiredRoles = {TeamRole.ROLE_MANAGER})
    public TaskResponseDTO create(@TeamId Long teamId, TaskCreateDTO dto) {

        if (dto.userId() != null) {
            if (!teamMemberService.isUserMemberOfTeam(teamId, dto.userId()))
                throw new TeamMemberNotFoundException("User is not a member of this team");
        }

        Task task = taskMapper.toEntity(dto);
        taskRepository.save(task);
        return taskMapper.toDto(task);
    }

    @Override
    @TeamAccess(requiredRoles = {TeamRole.ROLE_MODERATOR})
    public void delete(Long teamId, Long taskId) {
        boolean exist = taskRepository.existsById(taskId);
        if (!exist)
            throw new TaskNotFoundException("Task not found");
        taskRepository.deleteById(taskId);
    }

    @Override
    @Transactional
    @TeamAccess(requiredRoles = {TeamRole.ROLE_MEMBER})
    public TaskResponseDTO update(@TeamId Long teamId, TaskUpdateDTO dto) {
        Task task = taskRepository.findById(dto.taskId())
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        taskMapper.updateEntityFromDto(dto, task);
        jdbcAggregateTemplate.update(task);

        return taskMapper.toDto(task);
    }
}
