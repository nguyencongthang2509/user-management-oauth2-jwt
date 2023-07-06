package com.example.user_management.controller;

import com.example.user_management.entity.base.ResponseObject;
import com.example.user_management.model.request.CreateUserRequest;
import com.example.user_management.model.request.FindUserRequest;
import com.example.user_management.model.request.UpdateUserRequest;
import com.example.user_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseObject getAllUser(final FindUserRequest request) {
        return new ResponseObject(userService.findUser(request));
    }

    @GetMapping("/{id}")
    public ResponseObject findUserById(@PathVariable("id") UUID id) {
        return new ResponseObject(userService.findUserById(id));
    }

    @PostMapping
    public ResponseObject create(@RequestBody CreateUserRequest request){
        return new ResponseObject(userService.create(request));
    }

    @PutMapping()
    public ResponseObject update(@RequestBody UpdateUserRequest request){
        return new ResponseObject(userService.update(request));
    }

    @PostMapping("/{id}")
    public ResponseObject delete(@PathVariable("id") UUID id){
        return new ResponseObject(userService.delete(id));
    }
}
