package com.example.tasktracker.security.teamAccessUtils;

import com.example.tasktracker.enums.TeamRole;
import com.example.tasktracker.model.TeamMember;
import com.example.tasktracker.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class TeamAccessAspect {

    @Around("@annotation(com.example.tasktracker.security.teamAccessUtils.TeamAccess)")
    public Object checkTeamAccess(ProceedingJoinPoint joinPoint) throws Throwable {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!(principal instanceof CustomUserDetails userDetails)) {
            throw new AccessDeniedException("Unauthorized");
        }

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        TeamAccess teamAccess = signature.getMethod().getAnnotation(TeamAccess.class);
        TeamRole[] requiredRoles = teamAccess.requiredRoles();

        Long teamId = extractTeamIdFromArgs(signature, joinPoint.getArgs());
        if (teamId == null) {
            throw new SecurityException("teamId is required");
        }

        if (!hasRequiredRole(userDetails.teamRoles(), teamId, requiredRoles)) {
            throw new SecurityException("Access denied");
        }

        return joinPoint.proceed();
    }

    private Long extractTeamIdFromArgs(MethodSignature signature, Object[] args) {
        var parameterAnnotations = signature.getMethod().getParameterAnnotations();

        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (arg == null) continue;

            for (var annotation : parameterAnnotations[i]) {
                if (annotation.annotationType() == TeamId.class && arg instanceof Long teamId) {
                    return teamId;
                }
            }

            Long extracted = extractTeamIdFromObject(arg);
            if (extracted != null) return extracted;
        }

        return null;
    }

    private Long extractTeamIdFromObject(Object obj) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(TeamId.class) && field.getType().equals(Long.class)) {
                try {
                    field.setAccessible(true);
                    Object value = field.get(obj);
                    if (value instanceof Long teamId) {
                        return teamId;
                    }
                } catch (IllegalAccessException e) {
                    log.warn("Failed to access teamId from object: {}", obj, e);
                }
            }
        }
        return null;
    }

    private boolean hasRequiredRole(List<TeamMember> teamRoles, Long teamId, TeamRole[] requiredRoles) {
        return teamRoles.stream()
                .filter(role -> role.getTeamId().equals(teamId))
                .anyMatch(userRole ->
                        Arrays.stream(requiredRoles)
                                .anyMatch(required -> userRole.getTeamRole().hasAccessTo(required))
                );
    }
}
