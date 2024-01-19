package com.maximbuza.appaston.controller;


import com.maximbuza.appaston.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class MyController {
    @Autowired
    private UserService userService;


    @GetMapping("/showAllUsers")
    public Set<Map.Entry<String,String>> showAllUsers() {

        return userService.getAllUsers();
    }



}


