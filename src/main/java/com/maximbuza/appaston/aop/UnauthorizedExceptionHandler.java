package com.maximbuza.appaston.aop;

import com.maximbuza.appaston.exception.UnauthorizedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UnauthorizedExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<String> unauthorizedHandler(UnauthorizedException unauthorizedException) {
        return ResponseEntity.status(401).body(unauthorizedException.getMessage());
    }
}
