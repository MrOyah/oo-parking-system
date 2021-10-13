package com.oyah.ooparkingsystem.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.oyah.ooparkingsystem.entity.datamodel.Violation;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex,
        HttpHeaders headers, 
        HttpStatus status, 
        WebRequest request) {
        
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());

        List<Violation> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> getErrorMessage(error))
            .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, HttpStatus.BAD_REQUEST);
    }

    private Violation getErrorMessage(FieldError fieldError) {
        String fieldName = fieldError.getField();
        String message = fieldError.getDefaultMessage();
        String[] fieldNameSplit = fieldName.split("\\.");
        String[] messageSplit = message.split("\\|");
        
        fieldName = fieldNameSplit.length > 1 ?  
            String.format("%s.%s", fieldNameSplit[0], messageSplit[0]) : 
            messageSplit[0];
        message = messageSplit[1];
        return new Violation(fieldName, message);
    }
}
