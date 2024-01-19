package com.maximbuza.appaston.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MyController {

    @RequestMapping("/showAllUsers")
    public String showAllUsers(Model model) {
        List<User> allUsers = userService.getAllEmployees();
        model.addAttribute("allUsers", allUsers);
        return "all-users";
    }
    @RequestMapping("/singUp")
    public String singUpUser(Model model) {

    }
    @RequestMapping("/")
    public String singInUser(Model model) {

    }
    @RequestMapping("/changePassword")
    public String changePassword(Model model) {

    }


}
