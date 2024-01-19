package com.maximbuza.appaston.controller;

import com.maximbuza.appaston.service.UserService;
import com.maximbuza.appaston.storage.UserPasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class MyController {
    public MyController(@Autowired UserService userService) {
        this.userService = userService;
    }

    UserService userService;

    @GetMapping("/showAllUsers")
    public Set<Map.Entry<String,String>> showAllUsers(Model model) {
     return userService.getAllUsers();
    }

//    @RequestMapping("/singUp")
//    public String singUpUser(Model model) {
//
//    }
//
//    @RequestMapping("/")
//    public String singInUser(Model model) {
//
//    }
//
//    @RequestMapping("/changePassword")
//    public String changePassword(Model model) {
//
//    }


}
