package com.example.tasktracker.security;

import com.example.tasktracker.model.TeamMember;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

@Aspect
@Component
@RequiredArgsConstructor
public class TeamAccessAspect {

    private final HttpServletRequest request;

    @Around("@annotation(com.example.tasktracker.security.TeamAccess)")
    public Object checkTeamAccess(ProceedingJoinPoint joinPoint) throws Throwable {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!(principal instanceof CustomUserDetails userDetails)) {
            throw new SecurityException("Unauthorized");
        }

        Long teamId = extractTeamIdFromRequest();

        if (teamId == null) {
            throw new SecurityException("teamId is required");
        }

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        TeamAccess teamAccess = method.getAnnotation(TeamAccess.class);
        String requiredRole = teamAccess.requiredRole();

        if (!hasRequiredRole(userDetails.teamRoles(), teamId, requiredRole)) {
            throw new SecurityException("Access denied");
        }

        return joinPoint.proceed();
    }

    private Long extractTeamIdFromRequest() {
        try {
            String path = request.getRequestURI();
            String[] parts = path.split("/");
            for (int i = 0; i < parts.length; i++) {
                if (parts[i].equals("teams") && i + 1 < parts.length) {
                    return Long.parseLong(parts[i + 1]);
                }
            }
        } catch (NumberFormatException ignored) {
        }
        return null;
    }

    private boolean hasRequiredRole(List<TeamMember> teamRoles, Long teamId, String requiredRole) {
        return teamRoles.stream()
                .anyMatch(role -> role.getTeamId().equals(teamId) && role.getTeamRole().getValue().equals(requiredRole));
    }
}
