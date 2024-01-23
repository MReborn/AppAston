package com.maximbuza.appaston.aop;

import com.maximbuza.appaston.exception.BadDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BadDataExceptionHandler {
    @ExceptionHandler(BadDataException.class)
    public ResponseEntity<String> handlerBadData(BadDataException badDataException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(badDataException.getMessage());
    }

}
