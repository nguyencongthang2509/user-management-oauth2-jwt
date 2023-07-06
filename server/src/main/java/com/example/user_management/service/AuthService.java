package com.example.user_management.service;

import com.example.user_management.model.response.JwtResponse;

public interface AuthService {

    JwtResponse login(String tokenId);
}
