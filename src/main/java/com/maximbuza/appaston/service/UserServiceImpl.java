package com.maximbuza.appaston.service;

import com.maximbuza.appaston.dto.LoginAndRegistrationUserRequestDTO;
import com.maximbuza.appaston.dto.ChangerPasswordRequestDTO;
import com.maximbuza.appaston.storage.OperationsForStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    public UserServiceImpl(@Autowired OperationsForStorage operationsForStorage) {
        this.operationsForStorage = operationsForStorage;
    }

    private final OperationsForStorage operationsForStorage;


    @Override
    public String getAllUsers() {
        return operationsForStorage.giveAllUser();
    }

    @Override
    public String singUpUser(LoginAndRegistrationUserRequestDTO loginAndRegistrationUserRequestDTO) {
        return operationsForStorage.signUpUser(loginAndRegistrationUserRequestDTO);

    }

    @Override
    public String singInUser(LoginAndRegistrationUserRequestDTO loginAndRegistrationUserRequestDTO) {
        return operationsForStorage.signInUser(loginAndRegistrationUserRequestDTO);
    }

    @Override
    public String changePassword(ChangerPasswordRequestDTO changerPasswordRequestDTO) {
        return operationsForStorage.changePassword(changerPasswordRequestDTO);
    }
}
