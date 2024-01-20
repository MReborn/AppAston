package com.maximbuza.appaston.storage;

import static com.maximbuza.appaston.storage.CheckerCorrectnessUsernamePassword.*;
import static com.maximbuza.appaston.storage.Storage.*;

import com.maximbuza.appaston.dto.LoginAndRegistrationUserRequestDTO;
import com.maximbuza.appaston.dto.ChangerPasswordRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class OperationsForStorage {

    public String giveAllUser() { // метод возвращает список всех аккаунтов через строку
        return "List of user usernames and passwords:\n" + userAccounts.entrySet();
    }

    public String signUpUser(LoginAndRegistrationUserRequestDTO user) { //регистрация пользователя, тащит из сервиса requestbody с данными о пользователя
        String usernamePossible = user.getUsername(); //получает из контейнера данные
        String passwordPossible = user.getPassword();
        if (isUserIncorrect(usernamePossible)) { // проверка на корректность username, если не прошел проверку то соответственный мессаж
            return "Username is incorrect format";
        }
        if (isUserExist(usernamePossible)) { // существует ли уже пользователь в контейнере?
            return "Oh no! The user has already been added once";
        }
        if (isPasswordIncorrectFormat(passwordPossible)) { // проверка на некорректный пароль
            return "Password is incorrect format :(";
        }
        setPassword(usernamePossible,passwordPossible); // если все проверки пройдены то помещает данные в контейнер
        return "User has been added:\nlogin: " + usernamePossible + "\npassword: " + passwordPossible;
    }


    public String signInUser(LoginAndRegistrationUserRequestDTO user) { // метод для входа пользователя, также тянет данные пользователя
        String username = user.getUsername();
        String password = user.getPassword();

        if (isUserIncorrect(username)) {
            return "Username is incorrect";
        }
        if (!isUserExist(username)) {
            return "The user was not found";
        }
        if (isPasswordIncorrectFormat(password)) {
            return "Password is incorrect format:(";
        }
        if (isPasswordMatch(username, password)) { // проверяет на совпадение пароля с хранилищем если всё ок то говорит что успешный вход
            return "Successful login. Congratulations";
        } else return "Wrong password";

    }

    public String changePassword(ChangerPasswordRequestDTO changerPasswordRequestDTO) { // метод по смене пароля тянет с сервиса данные о имени старом и новом пароле
        String username = changerPasswordRequestDTO.getUsername();
        String oldPassword = changerPasswordRequestDTO.getOldPassword();
        String newPassword = changerPasswordRequestDTO.getNewPassword();

        if (isUserIncorrect(username)) {
            return "Username is incorrect";
        }
        if (!isUserExist(username)) {
            return "The user was not found";
        }
        if (isPasswordIncorrectFormat(oldPassword) || isPasswordIncorrectFormat(newPassword)) {
            return "Some of the Passwords in the wrong format :(";
        }
        if (isPasswordMatch(username, oldPassword)) { // если все условия выполнены то пароль сменится
            setPassword(username, newPassword);
            return "Password was changed successfully. Your new login details:\nusername: "+username+"\npassword: "+newPassword;
        } else {
            return "Wrong password";
        }
    }
}