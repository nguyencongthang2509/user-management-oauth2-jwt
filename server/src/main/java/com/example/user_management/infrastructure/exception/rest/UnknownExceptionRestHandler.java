package com.example.user_management.infrastructure.exception.rest;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public final class UnknownExceptionRestHandler extends
        UserManagementExceptionRestHandler<Exception> {

    @Override
    protected Object wrapApi(Exception ex) {
        return ex.getMessage();
    }
}
