package com.example.tasktracker.mapper;

import com.example.tasktracker.dto.taskDtos.TaskCreateDTO;
import com.example.tasktracker.dto.taskDtos.TaskResponseDTO;
import com.example.tasktracker.dto.taskDtos.TaskUpdateDTO;
import com.example.tasktracker.enums.TaskPriority;
import com.example.tasktracker.enums.TaskStatus;
import com.example.tasktracker.model.Task;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TaskMapper {

    Task toEntity(TaskCreateDTO dto);

    @Mapping(source = "priority", target = "priority", qualifiedByName = "mapPriorityToString")
    @Mapping(source = "status", target = "status", qualifiedByName = "mapStatusToString")
    TaskResponseDTO toDto(Task task);

    void updateEntityFromDto(TaskUpdateDTO dto, @MappingTarget Task task);

    @Named("mapPriorityToString")
    static String mapPriorityToString(TaskPriority priority) {
        return priority != null ? priority.getValue() : null;
    }

    @Named("mapStatusToString")
    static String mapStatusToString(TaskStatus status) {
        return status != null ? status.getValue() : null;
    }
}


