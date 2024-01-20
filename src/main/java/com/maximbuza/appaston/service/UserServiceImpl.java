package com.maximbuza.appaston.service;

import com.maximbuza.appaston.dto.LoginAndRegistrationUserRequestDTO;
import com.maximbuza.appaston.dto.ChangerPasswordRequestDTO;
import com.maximbuza.appaston.storage.OperationsForStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    public UserServiceImpl(@Autowired OperationsForStorage operationsForStorage) { // внедрется зависимость от класса работающего с хранилищем
        this.operationsForStorage = operationsForStorage;
    }

    private final OperationsForStorage operationsForStorage;


    @Override
    public String getAllUsers() {
        return operationsForStorage.giveAllUser();
    } // просит вернуть всех юзеров класс, работающий с хранилищем

    @Override
    public String singUpUser(LoginAndRegistrationUserRequestDTO loginAndRegistrationUserRequestDTO) { //регистрация нового пользователя
        return operationsForStorage.signUpUser(loginAndRegistrationUserRequestDTO);// от контейнера перекидывает данные о пользователе классу, работающему с хранилищем

    }

    @Override
    public String singInUser(LoginAndRegistrationUserRequestDTO loginAndRegistrationUserRequestDTO) { //вход юзера
        return operationsForStorage.signInUser(loginAndRegistrationUserRequestDTO);// от контейнера перекидывает данные о пользователе классу, работающему с хранилищем
    }

    @Override
    public String changePassword(ChangerPasswordRequestDTO changerPasswordRequestDTO) { //смена пароля
        return operationsForStorage.changePassword(changerPasswordRequestDTO);// от контейнера перекидывает данные о пользователе классу, работающему с хранилищем
    }
}
