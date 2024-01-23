package com.maximbuza.appaston.controller;


import com.maximbuza.appaston.dto.User;
import com.maximbuza.appaston.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api") // к этому контроллеру обращаться через /api
public class UserController {
    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    } // прикрепили сервис к контроллеру

    private final UserService userService;


    @GetMapping("/getAllUsers") // по этому адресу можно вызвать get запрос и получить список юзеров
    public ResponseEntity<String> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    } // делегирует запрос сервису

    @PostMapping("/signUpUser") //регистрация юзера
    public ResponseEntity<String> signUpUser(@RequestBody User user) { // образует контейнер данных пользователя и передает сервису
        return ResponseEntity.status(201).body(userService.signUpUser(user));
    }

    @PostMapping("/signInUser") //вход юзера
    public ResponseEntity<String> signInUser(@RequestBody User user) { // образует контейнер данных пользователя и передает сервису
        return ResponseEntity.ok(userService.signInUser(user));
    }

    @PostMapping("/changePassword") //смена пароля
    public ResponseEntity<String> changePassword(@RequestBody User user) { // образует контейнер данных пользователя и передает сервису
        return ResponseEntity.ok(userService.changePassword(user));
    }
}


