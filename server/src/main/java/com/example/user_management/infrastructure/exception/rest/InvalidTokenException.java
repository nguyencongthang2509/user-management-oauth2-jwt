package com.example.user_management.infrastructure.exception.rest;

import com.example.user_management.infrastructure.constant.Message;

public class InvalidTokenException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private String message;

    public InvalidTokenException() {
    }

    public InvalidTokenException(Message statusCode) {
        this.message = statusCode.getMessage();
    }

    public InvalidTokenException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
