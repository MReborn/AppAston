package com.maximbuza.appaston.controller;


import com.maximbuza.appaston.dto.User;
import com.maximbuza.appaston.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api") // к этому контроллеру обращаться через /api
public class UserController {
    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    } // прикрепили сервис к контроллеру

    private final UserService userService;


    @GetMapping("/getAllUsers") // по этому адресу можно вызвать get запрос и получить список юзеров
    public String getAllUsers() {
        return userService.getAllUsers();
    } // делегирует запрос сервису

    @PostMapping("/signUpUser") //регистрация юзера
    public String signUpUser(@RequestBody User user) { // образует контейнер данных пользователя и передает сервису
        return userService.signUpUser(user);
    }

    @PostMapping("/signInUser") //вход юзера
    public String signInUser(@RequestBody User user) { // образует контейнер данных пользователя и передает сервису
        return userService.signInUser(user);
    }

    @PostMapping("/changePassword") //смена пароля
    public String changePassword(@RequestBody User user) { // образует контейнер данных пользователя и передает сервису
        return userService.changePassword(user);
    }
}

