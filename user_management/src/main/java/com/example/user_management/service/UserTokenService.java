package com.example.user_management.service;

import com.example.user_management.entity.UserToken;
import com.example.user_management.model.response.JwtResponse;

import java.util.UUID;

public interface UserTokenService {

    UserToken findUserTokenByToken(String token);

    UserToken findUserTokenByUserId(UUID userId);

    void save(UserToken userToken);

    JwtResponse findUserByToken(String token);
}
