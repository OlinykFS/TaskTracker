package com.example.tasktracker.exceptions;

import com.example.tasktracker.dto.ErrorResponse;
import com.example.tasktracker.exceptions.taskExceptions.TaskNotFoundException;
import com.example.tasktracker.exceptions.teamExceptions.TeamEmptyException;
import com.example.tasktracker.exceptions.teamExceptions.TeamMemberAlreadyExistException;
import com.example.tasktracker.exceptions.teamExceptions.TeamMemberNotFoundException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomRoleNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomRoleNotFoundException(CustomRoleNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage(),"ROLE_ERROR", HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(TeamMemberNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTeamMemberNotFoundException(TeamMemberNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage(),"TEAM_MEMBER_NOT_FOUND", HttpStatus.NOT_FOUND.value());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTaskNotFoundException(TaskNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage(), "Task not found", HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(TeamEmptyException.class)
    public ResponseEntity<ErrorResponse> handleTeamIsEmptyException(TeamEmptyException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage(),"Team is Empty", HttpStatus.NO_CONTENT.value());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(TeamMemberAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleTeamMemberAlreadyExistException(TeamMemberAlreadyExistException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage(),"Team Member already exists", HttpStatus.CONFLICT.value());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex) {
        ErrorResponse error = new ErrorResponse("Access denied: " + ex.getMessage(), "ACCESS_DENIED", HttpStatus.FORBIDDEN.value());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException ex) {
        ErrorResponse error = new ErrorResponse("Authentication error: " + ex.getMessage(), "AUTH_ERROR", HttpStatus.UNAUTHORIZED.value());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrorResponse> handleJwtException(JwtException ex) {
        ErrorResponse error = new ErrorResponse("JWT error: " + ex.getMessage(), "JWT_ERROR", HttpStatus.UNAUTHORIZED.value());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        ErrorResponse error = new ErrorResponse("Email already exists: " + ex.getMessage(), "EMAIL_CONFLICT", HttpStatus.CONFLICT.value());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        ErrorResponse error = new ErrorResponse("Internal server error: " + ex.getMessage(), "SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        });

        ErrorResponse errorResponse = new ErrorResponse("Validation failed", "VALIDATION_ERROR", HttpStatus.BAD_REQUEST.value(), fieldErrors);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(),"ILLEGAL_ARGUMENT", HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleMissingRequestBody(HttpMessageNotReadableException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Invalid request format or missing body","INVALID_REQUEST", HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.badRequest().body(errorResponse);
    }



}
