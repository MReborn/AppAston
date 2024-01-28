package com.maximbuza.appaston.controller;


import com.maximbuza.appaston.dto.ChangePasswordDTO;
import com.maximbuza.appaston.dto.UserDTO;
import com.maximbuza.appaston.service.ServiceBD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api") // к этому контроллеру обращаться через /api
public class UserController {
    public UserController(@Autowired ServiceBD serviceBD) {
        this.serviceBD = serviceBD;
    } // прикрепили сервис к контроллеру

    private final ServiceBD serviceBD;

    @GetMapping("/getAllUsersFromBD") // по этому адресу можно вызвать get запрос и получить список юзеров
    public ResponseEntity<String> getAllUsersFromBd() {
        return ResponseEntity.ok(serviceBD.getAllUsers());
    } // делегирует запрос сервису

    @PostMapping("/signUpUserForBD") //регистрация юзера
    public ResponseEntity<String> signUpUserForBD(@RequestBody UserDTO user) { // образует контейнер данных пользователя и передает сервису
        return ResponseEntity.status(201).body(serviceBD.signUpUser(user));
    }

    @PostMapping("/signInUserForBD") //вход юзера
    public ResponseEntity<String> signInUserForBD(@RequestBody UserDTO user) { // образует контейнер данных пользователя и передает сервису
        return ResponseEntity.ok(serviceBD.signInUser(user));
    }

    @PostMapping("/changePasswordForBD") //смена пароля
    public ResponseEntity<String> changePasswordFromBD(@RequestBody ChangePasswordDTO changePasswordDTO) { // образует контейнер данных пользователя и передает сервису
        return ResponseEntity.ok(serviceBD.changePassword(changePasswordDTO));
    }
}


