package com.maximbuza.appaston.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Enum-класс для типов исключений.
 */
@Getter
@AllArgsConstructor
public enum CustomExceptionType {
    BAD_DATA("Bad data", HttpStatus.BAD_REQUEST),
    CONFLICT("Conflict", HttpStatus.CONFLICT),
    DATABASE_ERROR("Database error", HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_FOUND("Database error", HttpStatus.NOT_FOUND),
    UNAUTHORIZED("Database error", HttpStatus.UNAUTHORIZED);

    private final String defaultTitle;
    private final HttpStatus httpStatus;

}