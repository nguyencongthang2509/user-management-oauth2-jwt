package com.example.user_management.service.impl;

import com.example.user_management.entity.User;
import com.example.user_management.infrastructure.constant.Message;
import com.example.user_management.infrastructure.exception.rest.RestApiException;
import com.example.user_management.infrastructure.security.CustomUserDetails;
import com.example.user_management.infrastructure.security.CustomUserDetailsService;
import com.example.user_management.infrastructure.security.JwtTokenProvider;
import com.example.user_management.model.request.LoginRequest;
import com.example.user_management.model.response.JwtResponse;
import com.example.user_management.repository.UserRepository;
import com.example.user_management.repository.UserTokenRepository;
import com.example.user_management.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Validated
public class AuthServiceImpl implements AuthService {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTokenRepository userTokenRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public JwtResponse loginGoogle(String tokenId) {
        User userVerify = GoogleTokenVerifier.verifyToken(tokenId);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(userVerify.getEmail());
        System.out.println(userDetails);
        if (userDetails == null) {
            throw new RestApiException(Message.USER_NOT_EXISTS);
        }
        User userFind = userRepository.findUserByEmail(userVerify.getEmail());
        String jwtToken = jwtTokenProvider.generateTokenUser(userFind);
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setToken(jwtToken);
        return jwtResponse;
    }

    @Override
    public JwtResponse loginBasic(@RequestBody LoginRequest loginRequest) {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
//        } catch (BadCredentialsException e) {
//            throw new RestApiException(Message.EMAIL_OR_PASSWORD_INCORRECT);
//        }
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getEmail());
        if (!passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
            throw new RestApiException(Message.EMAIL_OR_PASSWORD_INCORRECT);
        }
        User userFind = userRepository.findUserByEmail(loginRequest.getEmail());
        String jwtToken = jwtTokenProvider.generateTokenUser(userFind);
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setToken(jwtToken);
        return jwtResponse;
    }

    @Override
    public String logout(String token) {
        userTokenRepository.deleteUserTokenByToken(token);
        return "Đăng xuất thành công !";
    }
}
