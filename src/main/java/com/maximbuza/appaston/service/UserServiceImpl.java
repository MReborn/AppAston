package com.maximbuza.appaston.service;

import com.maximbuza.appaston.dto.LoginAndRegistrationUserRequestDTO;
import com.maximbuza.appaston.dto.ChangerPasswordRequestDTO;
import com.maximbuza.appaston.operations.StorageOperationsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    public UserServiceImpl(@Autowired StorageOperationsImpl storageOperations) { // внедрется зависимость от класса работающего с хранилищем
        this.storageOperations = storageOperations;
    }

    private final StorageOperationsImpl storageOperations;


    @Override
    public String getAllUsers() {
        return storageOperations.giveAllUser();
    } // просит вернуть всех юзеров класс, работающий с хранилищем

    @Override
    public String singUpUser(LoginAndRegistrationUserRequestDTO loginAndRegistrationUserRequestDTO) { //регистрация нового пользователя
        return storageOperations.signUpUser(loginAndRegistrationUserRequestDTO);// от контейнера перекидывает данные о пользователе классу, работающему с хранилищем

    }

    @Override
    public String singInUser(LoginAndRegistrationUserRequestDTO loginAndRegistrationUserRequestDTO) { //вход юзера
        return storageOperations.signInUser(loginAndRegistrationUserRequestDTO);// от контейнера перекидывает данные о пользователе классу, работающему с хранилищем
    }

    @Override
    public String changePassword(ChangerPasswordRequestDTO changerPasswordRequestDTO) { //смена пароля
        return storageOperations.changePassword(changerPasswordRequestDTO);// от контейнера перекидывает данные о пользователе классу, работающему с хранилищем
    }
}
