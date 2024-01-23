package com.maximbuza.appaston.aop;

import com.maximbuza.appaston.exception.ConflictException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ConflictExceptionHandler {
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<String> conflictHandler(ConflictException conflictException) {
        return ResponseEntity.status(409).body(conflictException.getMessage());
    }
}
