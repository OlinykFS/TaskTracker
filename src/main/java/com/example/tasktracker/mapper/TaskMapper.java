package com.example.tasktracker.mapper;

import com.example.tasktracker.dto.taskDtos.TaskCreateDTO;
import com.example.tasktracker.dto.taskDtos.TaskResponseDTO;
import com.example.tasktracker.dto.taskDtos.TaskUpdateDTO;
import com.example.tasktracker.model.Task;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TaskMapper {

    Task toEntity(TaskCreateDTO dto);

    TaskResponseDTO toDto(Task task);

    void updateEntityFromDto(TaskUpdateDTO dto, @MappingTarget Task task);
}
