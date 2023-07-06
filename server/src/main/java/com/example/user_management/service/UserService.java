package com.example.user_management.service;

import com.example.user_management.entity.User;
import com.example.user_management.entity.base.PageableObject;
import com.example.user_management.model.request.CreateUserRequest;
import com.example.user_management.model.request.FindUserRequest;
import com.example.user_management.model.request.UpdateUserRequest;
import com.example.user_management.model.response.UserResponse;
import jakarta.validation.Valid;

import java.util.UUID;

public interface UserService {

    PageableObject<UserResponse> findUser(final FindUserRequest request);

    User findUserById(UUID id);

    User create(@Valid CreateUserRequest request);

    User update(@Valid UpdateUserRequest request);

    UUID delete(UUID id);

    User findUserByEmail(String email);

}
