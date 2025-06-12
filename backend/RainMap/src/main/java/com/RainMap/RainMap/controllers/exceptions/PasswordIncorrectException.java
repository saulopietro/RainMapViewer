package com.RainMap.RainMap.controllers.exceptions;

public class PasswordIncorrectException extends RuntimeException {

    public PasswordIncorrectException(String message) {
        super(message);
    }
}
