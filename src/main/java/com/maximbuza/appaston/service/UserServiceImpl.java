package com.maximbuza.appaston.service;

import com.maximbuza.appaston.dto.SignInAndUpRequestDTO;
import com.maximbuza.appaston.dto.ChangerPasswordRequestDTO;
import org.springframework.stereotype.Service;

import static com.maximbuza.appaston.operations.StorageOperations.*;


@Service
public class UserServiceImpl implements UserService {

    @Override
    public String getAllUsers() {
        return "List of user usernames and passwords:\n" + giveAllUsersFromStorage();
    } // просит вернуть всех юзеров класс, работающий с хранилищем

    @Override
    public String signUpUser(SignInAndUpRequestDTO signInSignUpRequestDTO) { //регистрация нового пользователя
        String usernamePossible = signInSignUpRequestDTO.getUsername(); //получает из контейнера данные
        String passwordPossible = signInSignUpRequestDTO.getPassword();
        if (isUserIncorrect(usernamePossible)) { // проверка на корректность username, если не прошел проверку то соответственный мессаж
            return "Username is incorrect format";
        }
        if (isUserExist(usernamePossible)) { // существует ли уже пользователь в контейнере?
            return "Oh no! The user has already been added once";
        }
        if (isPasswordIncorrectFormat(passwordPossible)) { // проверка на некорректный пароль
            return "Password is incorrect format :(";
        }
        setPassword(usernamePossible, passwordPossible); // если все проверки пройдены то помещает данные в хранилище через спецкласс
        return "User has been added:\nlogin: " + usernamePossible + "\npassword: " + passwordPossible;
    }

    public static boolean isUserIncorrect(String username) {
        return username.equals("");
    } // проверка пустая ли строка

    public static boolean isPasswordIncorrectFormat(String password) {
        return password.equals("");
    } // проверяет пустой ли пароль и возвращает правду если пустой

    @Override
    public String signInUser(SignInAndUpRequestDTO signInSignUpRequestDTO) { //вход юзера
        String username = signInSignUpRequestDTO.getUsername();
        String password = signInSignUpRequestDTO.getPassword();
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


    @Override
    public String changePassword(ChangerPasswordRequestDTO changerPasswordRequestDTO) { //смена пароля
        String username = changerPasswordRequestDTO.getUsernameChangerDTO();
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
            return "Password was changed successfully. Your new login details:\nusername: " + username + "\npassword: " + newPassword;
        } else {
            return "Wrong password";
        }
    }
    public static boolean isPasswordMatch(String username, String passwordPossible) { // метод проверяет совпадают ли пароль в хранилище с переданным паролем
        return getPasswordFromStorage(username).equals(passwordPossible); // подтягивает через юзернейм и сравнивает с параметром
    }


}
