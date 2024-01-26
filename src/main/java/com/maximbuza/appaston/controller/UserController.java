package com.maximbuza.appaston.controller;


import com.maximbuza.appaston.dto.User;
import com.maximbuza.appaston.entity.UserEntity;
import com.maximbuza.appaston.service.ServiceBD;
import com.maximbuza.appaston.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api") // к этому контроллеру обращаться через /api
public class UserController {
    public UserController(@Autowired UserService userService, @Autowired ServiceBD serviceBD) {
        this.userService = userService;
        this.serviceBD = serviceBD;
    } // прикрепили сервис к контроллеру

    private final UserService userService;
    private final ServiceBD serviceBD;


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

    @GetMapping("/getAllUsersFromBD") // по этому адресу можно вызвать get запрос и получить список юзеров
    public ResponseEntity<String> getAllUsersFromBd() {
        return ResponseEntity.ok(serviceBD.getAllUsersFromBd());
    } // делегирует запрос сервису

    @PostMapping("/signUpUserForBD") //регистрация юзера
    public ResponseEntity<String> signUpUserForBD(@RequestBody User user) { // образует контейнер данных пользователя и передает сервису
        return ResponseEntity.status(201).body(serviceBD.signUpUser(user));
    }
}


