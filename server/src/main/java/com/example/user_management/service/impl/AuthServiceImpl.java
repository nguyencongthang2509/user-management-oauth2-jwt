package com.example.user_management.service.impl;

import com.example.user_management.entity.User;
import com.example.user_management.infrastructure.constant.Message;
import com.example.user_management.infrastructure.exception.rest.RestApiException;
import com.example.user_management.infrastructure.security.JwtTokenProvider;
import com.example.user_management.model.response.JwtResponse;
import com.example.user_management.service.AuthService;
import com.example.user_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @Override
    public JwtResponse login(String tokenId) {
        User userVerify = GoogleTokenVerifier.verifyToken(tokenId);
        User user = userService.findUserByEmail(userVerify.getEmail());
        if(user == null) {
            throw new RestApiException(Message.USER_NOT_EXISTS);
        }
        String jwtToken = jwtTokenProvider.generateTokenUser(user);
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setEmail(user.getEmail());
        jwtResponse.setRole(user.getRole().ordinal());
        jwtResponse.setFullName(user.getFullName());
        jwtResponse.setToken(jwtToken);
        jwtResponse.setIdUser(user.getId());
        return jwtResponse;
    }
}
