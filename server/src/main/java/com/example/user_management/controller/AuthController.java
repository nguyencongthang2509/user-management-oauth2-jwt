package com.example.user_management.controller;

import com.example.user_management.entity.base.ResponseObject;
import com.example.user_management.model.request.CreateUserRequest;
import com.example.user_management.model.request.LoginRequest;
import com.example.user_management.service.AuthService;
import com.example.user_management.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authentication")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping("/login-google/{tokenId}")
    public ResponseObject loginGoogle(@PathVariable("tokenId") String tokenId) {
        return new ResponseObject(authService.loginGoogle(tokenId));
    }

    @PostMapping("/login-basic")
    public ResponseObject loginBasic(@RequestBody LoginRequest request) {
        return new ResponseObject(authService.loginBasic(request));
    }

    @PostMapping("/register")
    public ResponseObject register(@RequestBody CreateUserRequest request) {
        return new ResponseObject(userService.create(request));
    }

    @PostMapping("/logout")
    public ResponseObject logout(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        String token = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
        }
        new SecurityContextLogoutHandler().logout(request, null, null);
        return new ResponseObject(authService.logout(token));
    }

}
