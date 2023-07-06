package com.example.user_management.infrastructure.exception.rest;

import com.example.user_management.infrastructure.exception.UserManagementExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class UserManagementExceptionRestHandler<Z extends Exception>
        extends UserManagementExceptionHandler<ResponseEntity<?>,Z> {

    @Override
    protected ResponseEntity<?> wrap(Z ex) {
        return new ResponseEntity<>(wrapApi(ex), HttpStatus.BAD_REQUEST);
    }

    protected abstract Object wrapApi(Z ex);
}
