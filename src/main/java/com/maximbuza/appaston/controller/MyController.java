package com.maximbuza.appaston.controller;


import com.maximbuza.appaston.dto.LoginAndRegistrationUserRequestDTO;
import com.maximbuza.appaston.dto.ChangerPasswordRequestDTO;
import com.maximbuza.appaston.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class MyController {
    @Autowired
    private UserService userService;


    @GetMapping("/showAllUsers")
    public String showAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/signUpUser")
    public String signUpUser(@RequestBody LoginAndRegistrationUserRequestDTO loginAndRegistrationUserRequestDTO) {
        return userService.singUpUser(loginAndRegistrationUserRequestDTO);
    }

    @PostMapping("/signInUser")
    public String signInUser(@RequestBody LoginAndRegistrationUserRequestDTO loginAndRegistrationUserRequestDTO) {
        return userService.singInUser(loginAndRegistrationUserRequestDTO);
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestBody ChangerPasswordRequestDTO changerPasswordRequestDTO) {
        return userService.changePassword(changerPasswordRequestDTO);
    }
}


