package com.maximbuza.appaston.service;

import com.maximbuza.appaston.dto.ChangePasswordDTO;
import com.maximbuza.appaston.dto.UserDTO;
import com.maximbuza.appaston.exception.BadDataException;
import com.maximbuza.appaston.exception.ConflictException;
import com.maximbuza.appaston.exception.NotFoundException;
import com.maximbuza.appaston.exception.UnauthorizedException;
import org.springframework.stereotype.Service;

import static com.maximbuza.appaston.domainService.ContainerService.*;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public String getAllUsers() {
        return "List of user usernames and passwords:\n" + giveAllUsersFromStorage();
    } // просит вернуть всех юзеров класс, работающий с хранилищем

    @Override
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


    @Override
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
            setPassword(username, newPassword);
            return "Password was changed successfully. Your new login details:\nusername: " + username + "\npassword: " + newPassword;
        } else {
            throw new UnauthorizedException("Wrong old password");
        }
    }

    public static boolean isPasswordMatch(String username, String passwordPossible) { // метод проверяет совпадают ли пароль в хранилище с переданным паролем
        return getPasswordFromStorage(username).equals(passwordPossible); // подтягивает через юзернейм и сравнивает с параметром
    }


}
