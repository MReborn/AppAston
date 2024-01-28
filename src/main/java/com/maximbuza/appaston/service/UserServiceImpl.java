package com.maximbuza.appaston.service;

import com.maximbuza.appaston.dto.ChangePasswordDTO;
import com.maximbuza.appaston.dto.UserDTO;
import com.maximbuza.appaston.entity.UserEntity;
import com.maximbuza.appaston.exception.*;
import com.maximbuza.appaston.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    public UserServiceImpl(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;

    public String getAllUsers() {
        return "List of user usernames and passwords:\n" + userRepository.getAllUsersFromBd();
    } // просит вернуть всех юзеров класс, работающий с хранилищем

    public String signUpUser(UserDTO user) { //регистрация нового пользователя
        String usernamePossible = user.getUsername(); //получает из контейнера данные
        String passwordPossible = user.getPassword();
        if (isUserIncorrect(usernamePossible)) { // проверка на корректность username, если не прошел проверку то соответственный мессаж
            throw new BadDataException("Username is incorrect");
        }
        if (isUserExist(usernamePossible)) { // существует ли уже пользователь в контейнере?
            throw new ConflictException("Oh no! The user has already been added once");
        }
        if (isPasswordIncorrectFormat(passwordPossible)) { // проверка на некорректный пароль
            throw new BadDataException("Password is incorrect format:(");
        }
        userRepository.saveOrUpdateUser(usernamePossible, passwordPossible); // если все проверки пройдены то помещает данные в хранилище через спецкласс
        return "User has been added:\nlogin: " + usernamePossible + "\npassword: " + passwordPossible;
    }

    public static boolean isUserIncorrect(String username) {
        return username.equals("");
    } // проверка пустая ли строка

    public boolean isUserExist(String username) { // проверяет существование пользователя в бд по юзернейму.
        return userRepository.findByUsername(username) != null;
    }

    public static boolean isPasswordIncorrectFormat(String password) {
        return password.equals("");
    } // проверяет пустой ли пароль и возвращает правду если пустой

    public String signInUser(UserDTO user) { //вход юзера
        String username = user.getUsername();
        String password = user.getPassword();
        if (isUserIncorrect(username)) {
            throw new BadDataException("Username is incorrect");
        }
        if (!isUserExist(username)) {
            throw new NotFoundException("The user was not found");
        }
        if (isPasswordIncorrectFormat(password)) {
            throw new BadDataException("Password is incorrect format:(");
        }
        if (isPasswordMatch(username, password)) { // проверяет на совпадение пароля с хранилищем если всё ок то говорит что успешный вход
            return "Successful login. Congratulations";
        } else throw new UnauthorizedException("Wrong password");
    }

    public boolean isPasswordMatch(String username, String passwordPossible) { // проверка на совпадение указанного пароля в параметрах
        UserEntity userEntity = userRepository.findByUsername(username);
        return userEntity.getPassword().equals(passwordPossible);
    }

    public String changePassword(ChangePasswordDTO changePasswordDTO) { //смена пароля
        String username = changePasswordDTO.getUsername();
        String oldPassword = changePasswordDTO.getOldPassword();
        String newPassword = changePasswordDTO.getNewPassword();

        if (isUserIncorrect(username)) {
            throw new BadDataException("Username is incorrect");
        }
        if (!isUserExist(username)) {
            throw new NotFoundException("The user was not found");
        }
        if (isPasswordIncorrectFormat(oldPassword) || isPasswordIncorrectFormat(newPassword)) {
            throw new BadDataException("Some of the Passwords in the wrong format :(");
        }
        if (isPasswordMatch(username, oldPassword)) { // если все условия выполнены то пароль сменится
            userRepository.saveOrUpdateUser(username, newPassword);
            return "Password was changed successfully. Your new login details:\nusername: " + username + "\npassword: " + newPassword;
        } else {
            throw new UnauthorizedException("Wrong old password");
        }
    }
}
