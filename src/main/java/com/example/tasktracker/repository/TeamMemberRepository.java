package com.example.tasktracker.repository;

import com.example.tasktracker.model.TeamMember;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamMemberRepository extends CrudRepository<TeamMember, Long> {

    List<TeamMember> getTeamMemberByTeamId(Long teamId);
    List<TeamMember> getTeamMemberByUserId(Long userId);

    List<TeamMember> getTeamMembersByUserId(Long userId);
}
