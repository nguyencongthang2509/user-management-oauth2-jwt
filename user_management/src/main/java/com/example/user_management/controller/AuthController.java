package com.example.user_management.controller;

import com.example.user_management.entity.base.ResponseObject;
import com.example.user_management.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authentication")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login/{tokenId}")
    public ResponseObject login(@PathVariable("tokenId") String tokenId) {
        return new ResponseObject(authService.login(tokenId));
    }

}
