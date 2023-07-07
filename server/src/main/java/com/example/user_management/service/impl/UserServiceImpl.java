package com.example.user_management.service.impl;

import com.example.user_management.entity.User;
import com.example.user_management.entity.base.PageableObject;
import com.example.user_management.infrastructure.constant.Message;
import com.example.user_management.infrastructure.exception.rest.RestApiException;
import com.example.user_management.model.request.CreateUserRequest;
import com.example.user_management.model.request.FindUserRequest;
import com.example.user_management.model.request.UpdateUserRequest;
import com.example.user_management.model.response.UserResponse;
import com.example.user_management.repository.UserRepository;
import com.example.user_management.service.UserService;
import com.example.user_management.util.ConvertStringToDate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.sql.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@Validated
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public PageableObject<UserResponse> findUser(final FindUserRequest request) {
        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize());
        return new PageableObject<>(userRepository.findUser(pageRequest, request));
    }

    @Override
    public User findUserById(UUID id) {
        Optional<User> userFind = userRepository.findById(id);
        if (!userFind.isPresent()) {
            throw new RestApiException(Message.USER_NOT_EXISTS);
        }
        if(userFind.isPresent()) {
            throw new RestApiException(Message.USER_NOT_EXISTS);
        }
        return userRepository.findById(id).get();
    }

    @Override
    public User create(@Valid CreateUserRequest request) {
        User newUser = new User();
        newUser.setAddress(request.getAddress());
        newUser.setEmail(request.getEmail());
        Date dateOfBirth = ConvertStringToDate.convert(request.getDateOfBirth(), "yyyy-MM-dd");
        newUser.setDateOfBirth(dateOfBirth);
        newUser.setGender(request.getGender());
        newUser.setFullName(request.getFullName());
        newUser.setPassword(request.getPassword());
        newUser.setPhoneNumber(request.getPhoneNumber());
        newUser.setRole(request.getRole());
        return userRepository.save(newUser);
    }

    @Override
    public User update(@Valid UpdateUserRequest request) {
        User userFind = userRepository.findById(request.getId()).get();
        if (userFind == null) {
            throw new RestApiException(Message.USER_NOT_EXISTS);
        }
        if(!userFind.getId().equals(request.getIdUserCurrent())){
            throw new RestApiException(Message.USER_NOT_ALLOWED);
        }
        userFind.setAddress(request.getAddress());
        userFind.setEmail(request.getEmail());
        Date dateOfBirth = ConvertStringToDate.convert(request.getDateOfBirth(), "yyyy-MM-dd");
        userFind.setDateOfBirth(dateOfBirth);
        userFind.setGender(request.getGender());
        userFind.setFullName(request.getFullName());
        userFind.setPassword(request.getPassword());
        userFind.setPhoneNumber(request.getPhoneNumber());
        userFind.setRole(request.getRole());
        return userRepository.save(userFind);
    }

    @Override
    public UUID delete(UUID id) {
        User userFind = userRepository.findById(id).get();
        if (userFind == null) {
            throw new RestApiException(Message.USER_NOT_EXISTS);
        }
        userRepository.deleteById(id);
        return id;
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findUser(email);
        return user;
    }

}
