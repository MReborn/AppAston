package com.maximbuza.appaston.service;

import com.maximbuza.appaston.dto.User;
import com.maximbuza.appaston.exception.*;
import com.maximbuza.appaston.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ServiceBD {
    public ServiceBD(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;

    public String getAllUsers() {
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
        userRepository.saveOrUpdateUser(user); // если все проверки пройдены то помещает данные в хранилище через спецкласс
        return "User has been added:\nlogin: " + usernamePossible + "\npassword: " + passwordPossible;
    }
    public static boolean isUserIncorrect(String username) {
        return username.equals("");
    } // проверка пустая ли строка

    public static boolean isPasswordIncorrectFormat(String password) {
        return password.equals("");
    } // проверяет пустой ли пароль и возвращает правду если пустой

    public String signInUser(User user) { //вход юзера
        String username = user.getUsername();
        String password = user.getPassword();
        if (isUserIncorrect(username)) {
            throw new BadDataException("Username is incorrect");
        }
        if (!userRepository.isUserExist(username)) {
            throw new NotFoundException("The user was not found");
        }
        if (isPasswordIncorrectFormat(password)) {
            throw new BadDataException("Password is incorrect format:(");
        }
        if (userRepository.isPasswordMatch(username, password)) { // проверяет на совпадение пароля с хранилищем если всё ок то говорит что успешный вход
            return "Successful login. Congratulations";
        } else throw new UnauthorizedException("Wrong password");
    }

    public String changePassword(User user) { //смена пароля
        String username = user.getUsername();
        String oldPassword = user.getPassword();
        String newPassword = user.getNewPassword();

        if (isUserIncorrect(username)) {
            throw new BadDataException("Username is incorrect");
        }
        if (!userRepository.isUserExist(username)) {
            throw new NotFoundException("The user was not found");
        }
        if (isPasswordIncorrectFormat(oldPassword) || isPasswordIncorrectFormat(newPassword)) {
            throw new BadDataException("Some of the Passwords in the wrong format :(");
        }
        if (userRepository.isPasswordMatch(username, oldPassword)) { // если все условия выполнены то пароль сменится
            userRepository.saveOrUpdateUser(user);
            return "Password was changed successfully. Your new login details:\nusername: " + username + "\npassword: " + newPassword;
        } else {
            throw new UnauthorizedException("Wrong old password");
        }
    }

}
