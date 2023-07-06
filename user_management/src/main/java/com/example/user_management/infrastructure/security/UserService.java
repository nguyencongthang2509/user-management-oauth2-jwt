package com.example.user_management.infrastructure.security;

import com.example.user_management.entity.User;
import com.example.user_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author thangncph26123
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findUserByEmail(String email) {
        User user = userRepository.findUser(email);
        return user;
    }
}
