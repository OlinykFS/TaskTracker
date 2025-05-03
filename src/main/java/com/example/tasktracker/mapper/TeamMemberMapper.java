package com.example.tasktracker.mapper;

import com.example.tasktracker.dto.teamMemberDtos.TeamMemberCreateDTO;
import com.example.tasktracker.dto.teamMemberDtos.TeamMemberResponseDTO;
import com.example.tasktracker.enums.TeamRole;
import com.example.tasktracker.model.TeamMember;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TeamMemberMapper {

    TeamMember toEntity(TeamMemberCreateDTO dto);

    @Mapping(source = "teamRole", target = "teamRole", qualifiedByName = "mapTeamRoleToString")
    TeamMemberResponseDTO toDto(TeamMember teamMember);

    @Named("mapTeamRoleToString")
    static String mapTeamRoleToString(TeamRole teamRole) {
        return teamRole != null ? teamRole.getValue() : null;
    }
}
