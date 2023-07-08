package com.example.user_management.service;

import com.example.user_management.model.request.LoginRequest;
import com.example.user_management.model.response.JwtResponse;
import jakarta.validation.Valid;

public interface AuthService {

    JwtResponse loginGoogle(String tokenId);

    JwtResponse loginBasic(@Valid LoginRequest request);

    String logout(String token);

}
