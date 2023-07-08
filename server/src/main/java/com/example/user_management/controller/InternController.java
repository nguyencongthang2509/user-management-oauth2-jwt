package com.example.user_management.controller;

import com.example.user_management.entity.base.ResponseObject;
import com.example.user_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/intern/user")
@CrossOrigin("*")
public class InternController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseObject getProfile(@PathVariable("id") UUID id) {
        return new ResponseObject(userService.findUserById(id));
    }
}
