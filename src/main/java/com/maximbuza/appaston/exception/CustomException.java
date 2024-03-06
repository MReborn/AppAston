package com.maximbuza.appaston.exception;

import lombok.Getter;

/**
 * Базовый класс кастомного исключения.
 */
@Getter
public class CustomException extends RuntimeException {

    private final CustomExceptionType type;

    public CustomException(CustomExceptionType type) {
        super(type.getDefaultTitle());
        this.type = type;
    }

    public CustomException(CustomExceptionType type, String additionalMessage) {
        super(type.getDefaultTitle() + ": " + additionalMessage);
        this.type = type;
    }

}