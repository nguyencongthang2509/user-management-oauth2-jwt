package com.example.user_management.controller;

import com.example.user_management.entity.base.ResponseObject;
import com.example.user_management.service.impl.UserTokenServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user-token")
public class UserTokenController {

    @Autowired
    private UserTokenServiceImpl userTokenService;

    @GetMapping("/get-me")
    public ResponseObject getMe(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        String token = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
        }
        return new ResponseObject(userTokenService.findUserByToken(token));
    }

}
