package com.example.user_management.controller;

import com.example.user_management.entity.User;
import com.example.user_management.infrastructure.security.JwtTokenProvider;
import com.example.user_management.infrastructure.security.UserService;
import com.example.user_management.model.response.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @GetMapping("/login/{tokenId}")
    public ResponseEntity<?> login(@PathVariable("tokenId") String tokenId) {
        try {
            User userVerify = GoogleTokenVerifier.verifyToken(tokenId);
            User user = userService.findUserByEmail(userVerify.getEmail());
            if (user != null) {
                String jwtToken = jwtTokenProvider.generateTokenUser(user);
                JwtResponse jwtResponse = new JwtResponse();
                jwtResponse.setEmail(user.getEmail());
                jwtResponse.setRole(user.getRole().ordinal());
                jwtResponse.setFullName(user.getFullName());
                jwtResponse.setToken(jwtToken);
                jwtResponse.setIdUser(user.getId());
                return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User không tồn tại");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred");
        }
    }
}
