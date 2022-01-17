package com.kryhowsky.shop.controller;

import com.kryhowsky.shop.mapper.FieldErrorMapper;
import com.kryhowsky.shop.model.dto.FieldErrorDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class AdviceController {

    private final FieldErrorMapper fieldErrorMapper;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public void handleEntityNotFoundException(EntityNotFoundException entityNotFoundException) {

        log.warn("Entity does not exist", entityNotFoundException);

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<FieldErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {

        log.warn("Validation failed", methodArgumentNotValidException);

        return methodArgumentNotValidException.getBindingResult().getAllErrors().stream()
                .map(fieldErrorMapper::toDto)
                .collect(Collectors.toList());

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public List<FieldErrorDto> handleConstraintViolationException(ConstraintViolationException constraintViolationException) {

        log.warn("Password and Confirm Password must be the same", constraintViolationException);

        return constraintViolationException.getConstraintViolations().stream()
                .map(constraintViolation -> new FieldErrorDto(constraintViolation.getMessage(), constraintViolation.getPropertyPath().toString()))
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void handleDataIntegrityViolationException(DataIntegrityViolationException dataIntegrityViolationException) {

        log.warn("Duplicated entry", dataIntegrityViolationException);

    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationServiceException.class)
    public void handleAuthenticationServiceException(AuthenticationServiceException authenticationServiceException) {

        log.warn("Login error", authenticationServiceException);

    }
}
