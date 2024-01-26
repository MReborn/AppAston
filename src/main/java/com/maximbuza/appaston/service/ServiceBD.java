package com.maximbuza.appaston.service;

import com.maximbuza.appaston.dto.User;
import com.maximbuza.appaston.exception.BadDataException;
import com.maximbuza.appaston.exception.ConflictException;
import com.maximbuza.appaston.repository.UserRepository;
import org.springframework.stereotype.Service;

import static com.maximbuza.appaston.domainService.ContainerService.isUserExist;
import static com.maximbuza.appaston.domainService.ContainerService.setPassword;
import static com.maximbuza.appaston.service.UserServiceImpl.*;
@Service
public class ServiceBD {
    public ServiceBD(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;

    public String getAllUsersFromBd() {
        return "List of user usernames and passwords:\n" + userRepository.getAllUsersFromBd();
    } // просит вернуть всех юзеров класс, работающий с хранилищем

    public String signUpUser(User user) { //регистрация нового пользователя
        String usernamePossible = user.getUsername(); //получает из контейнера данные
        String passwordPossible = user.getPassword();
        if (isUserIncorrect(usernamePossible)) { // проверка на корректность username, если не прошел проверку то соответственный мессаж
            throw new BadDataException("Username is incorrect");
        }
        if (userRepository.isUserExist(usernamePossible)) { // существует ли уже пользователь в контейнере?
            throw new ConflictException("Oh no! The user has already been added once");
        }
        if (isPasswordIncorrectFormat(passwordPossible)) { // проверка на некорректный пароль
            throw new BadDataException("Password is incorrect format:(");
        }
        userRepository.addUserOrUpdatePassword(usernamePossible, passwordPossible); // если все проверки пройдены то помещает данные в хранилище через спецкласс
        return "User has been added:\nlogin: " + usernamePossible + "\npassword: " + passwordPossible;
    }
}
