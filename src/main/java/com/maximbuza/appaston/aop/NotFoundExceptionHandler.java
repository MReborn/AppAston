package com.maximbuza.appaston.aop;

import com.maximbuza.appaston.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NotFoundExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> notFoundHandler(NotFoundException notFoundException) {
        return ResponseEntity.status(404).body(notFoundException.getMessage());
    }
}
