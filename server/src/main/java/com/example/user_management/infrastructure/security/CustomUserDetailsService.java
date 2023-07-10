package com.example.user_management.infrastructure.security;

import com.example.user_management.entity.User;
import com.example.user_management.infrastructure.constant.Message;
import com.example.user_management.infrastructure.exception.rest.RestApiException;
import com.example.user_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userInfo = userRepository.findUserByEmail(email);
        if (userInfo == null) {
            throw new RestApiException(Message.USER_NOT_EXISTS);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(String.valueOf(userInfo.getRole())));
        CustomUserDetails userDetails = new CustomUserDetails(
                new org.springframework.security.core.userdetails.User(
                        userInfo.getEmail(),
                        userInfo.getPassword(),
                        authorities
                )
        );
        userDetails.setId(userInfo.getId());
        userDetails.setFullName(userInfo.getFullName());
        userDetails.setRole(String.valueOf(userInfo.getRole()));
        return userDetails;
    }

}