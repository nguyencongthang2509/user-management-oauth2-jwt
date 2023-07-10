package com.example.user_management.service.impl;

import com.example.user_management.entity.UserToken;
import com.example.user_management.infrastructure.constant.Message;
import com.example.user_management.infrastructure.exception.rest.RestApiException;
import com.example.user_management.model.response.JwtResponse;
import com.example.user_management.model.response.UserResponse;
import com.example.user_management.repository.UserTokenRepository;
import com.example.user_management.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserTokenServiceImpl implements UserTokenService {

    @Autowired
    private UserTokenRepository userTokenRepository;

    public UserToken findUserTokenByToken(String token) {
        return userTokenRepository.findUserToken(token);
    }

    public UserToken findUserTokenByUserId(UUID userId) {
        return userTokenRepository.findUserTokenByIdUser(userId);
    }

    public void save(UserToken userToken) {
        userTokenRepository.save(userToken);
    }

    public JwtResponse findUserByToken(String token) {
        UserResponse userResponseFind = userTokenRepository.findUserByToken(token);
        if (userResponseFind == null) {
            throw new RestApiException(Message.USER_NOT_EXISTS);
        }
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setToken(token);
        return jwtResponse;
    }

}
