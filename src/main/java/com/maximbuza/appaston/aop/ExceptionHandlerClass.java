package com.maximbuza.appaston.aop;

import com.maximbuza.appaston.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerClass {
    @ExceptionHandler(BadDataException.class)
    public ResponseEntity<String> handlerBadData(BadDataException badDataException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(badDataException.getMessage());
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<String> conflictHandler(ConflictException conflictException) {
        return ResponseEntity.status(409).body(conflictException.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> notFoundHandler(NotFoundException notFoundException) {
        return ResponseEntity.status(404).body(notFoundException.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> unauthorizedHandler(UnauthorizedException unauthorizedException) {
        return ResponseEntity.status(401).body(unauthorizedException.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> databaseHandler(DatabaseException databaseException) {
        return ResponseEntity.status(500).body(databaseException.getMessage());
    }
}
