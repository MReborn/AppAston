package com.maximbuza.appaston.aop;

import com.maximbuza.appaston.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerClass {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handlerCustomException(CustomException exception) {
        return ResponseEntity.status(exception.getType().getHttpStatus()).body(exception.getMessage());
    }

}
