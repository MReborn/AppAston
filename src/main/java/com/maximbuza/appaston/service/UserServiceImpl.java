package com.maximbuza.appaston.service;

import com.maximbuza.appaston.dto.ChangePasswordDTO;
import com.maximbuza.appaston.dto.UserDTO;
import com.maximbuza.appaston.entity.UserEntity;
import com.maximbuza.appaston.exception.*;
import com.maximbuza.appaston.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Реализация интерфейса {@link UserService}
 */
@Service
public class UserServiceImpl implements UserService{
    public UserServiceImpl(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;

    private static final String INVALID_USERNAME_MESSAGE = "Username is incorrect";
    private static final String INVALID_PASSWORD_MESSAGE = "Password is incorrect format";
    private static final String NOT_FOUND_EX_MESSAGE = "The user was not found";
    private static final String CONFLICT_EX_MESSAGE = "Oh no! The user has already been added once";
    private static final String UNAUTHORIZED_EX_MESSAGE = "Wrong password";

    /**
     * Метод для получения всех пользователей.
     * @return {@link String}
     */
    public String getAllUsers() {
        List<UserEntity> userEntityList = userRepository.getAllUsersFromBd();
        List<UserDTO> userDTOList = new ArrayList<>();
        for(UserEntity userEntity: userEntityList){
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(userEntity.getUsername());
            userDTO.setPassword(userEntity.getPassword());
            userDTOList.add(userDTO);
        }
        return "List of user usernames and passwords:\n" + userDTOList;
    } // просит вернуть всех юзеров класс, работающий с хранилищем

    /**
     * Метод для регистрации нового пользователя.
     * @return {@link String}
     */
    public String signUpUser(UserDTO user) {
        String usernamePossible = user.getUsername(); //получает из контейнера данные
        String passwordPossible = user.getPassword();
        if (isUserIncorrect(usernamePossible)) { // проверка на корректность username, если не прошел проверку то соответственный мессаж
            throw new BadDataException(INVALID_USERNAME_MESSAGE);
        }
        if (isUserExist(usernamePossible)) { // существует ли уже пользователь в контейнере?
            throw new ConflictException(CONFLICT_EX_MESSAGE);
        }
        if (isPasswordIncorrectFormat(passwordPossible)) { // проверка на некорректный пароль
            throw new BadDataException(INVALID_PASSWORD_MESSAGE);
        }
        userRepository.saveOrUpdateUser(usernamePossible, passwordPossible); // если все проверки пройдены то помещает данные в хранилище через спецкласс
        return "User has been added:\nlogin: " + usernamePossible + "\npassword: " + passwordPossible;
    }

    public static boolean isUserIncorrect(String username) {
        return username.equals("");
    } // проверка пустая ли строка

    public boolean isUserExist(String username) { // проверяет существование пользователя в бд по юзернейму.
        return userRepository.findByUsername(username) != null;
    } // ищет пользователя по username. возвращает true если находит

    public static boolean isPasswordIncorrectFormat(String password) {
        return password.isEmpty();
    } // проверяет пустой ли пароль и возвращает правду если пустой

    /**
     * Метод для авторизации пользователя.
     * @return {@link String}
     */
    public String signInUser(UserDTO user) {
        String username = user.getUsername();
        String password = user.getPassword();
        if (isUserIncorrect(username)) {
            throw new BadDataException(INVALID_USERNAME_MESSAGE);
        }
        if (!isUserExist(username)) {
            throw new NotFoundException(NOT_FOUND_EX_MESSAGE);
        }
        if (isPasswordIncorrectFormat(password)) {
            throw new BadDataException(INVALID_PASSWORD_MESSAGE);
        }
        if (isPasswordMatch(username, password)) { // проверяет на совпадение пароля с хранилищем если всё ок то говорит что успешный вход
            return "Successful login. Congratulations";
        } else throw new UnauthorizedException(UNAUTHORIZED_EX_MESSAGE);
    }

    public boolean isPasswordMatch(String username, String passwordPossible) { // проверка на совпадение указанного пароля в параметрах
        UserEntity userEntity = userRepository.findByUsername(username);
        return userEntity.getPassword().equals(passwordPossible);
    }

    /**
     * Метод для смены пароля пользователя.
     * @return {@link String}
     */
    public String changePassword(ChangePasswordDTO changePasswordDTO) {
        String username = changePasswordDTO.getUsername();
        String oldPassword = changePasswordDTO.getOldPassword();
        String newPassword = changePasswordDTO.getNewPassword();

        if (isUserIncorrect(username)) {
            throw new BadDataException(INVALID_USERNAME_MESSAGE);
        }
        if (!isUserExist(username)) {
            throw new NotFoundException(NOT_FOUND_EX_MESSAGE);
        }
        if (isPasswordIncorrectFormat(oldPassword) || isPasswordIncorrectFormat(newPassword)) {
            throw new BadDataException(INVALID_PASSWORD_MESSAGE);
        }
        if (isPasswordMatch(username, oldPassword)) { // если все условия выполнены то пароль сменится
            userRepository.saveOrUpdateUser(username, newPassword);
            return "Password was changed successfully. Your new login details:\nusername: " + username + "\npassword: " + newPassword;
        } else {
            throw new UnauthorizedException(UNAUTHORIZED_EX_MESSAGE);
        }
    }
}
