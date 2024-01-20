package com.maximbuza.appaston.service;

import com.maximbuza.appaston.dto.LoginAndRegistrationUserRequestDTO;
import com.maximbuza.appaston.dto.ChangerPasswordRequestDTO;
import com.maximbuza.appaston.storage.UsernamePasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    public UserServiceImpl(@Autowired UsernamePasswordStorage usernamePasswordStorage) {
        this.usernamePasswordStorage = usernamePasswordStorage;
    }
    private UsernamePasswordStorage usernamePasswordStorage;


    @Override
    public String getAllUsers() {
        return usernamePasswordStorage.giveAllUser();
    }

    @Override
    public String singUpUser(LoginAndRegistrationUserRequestDTO loginAndRegistrationUserRequestDTO) {
        return usernamePasswordStorage.signUpUser(loginAndRegistrationUserRequestDTO);

    }

    @Override
    public String singInUser(LoginAndRegistrationUserRequestDTO loginAndRegistrationUserRequestDTO) {
        return usernamePasswordStorage.signInUser(loginAndRegistrationUserRequestDTO);
    }

    @Override
    public String changePassword(ChangerPasswordRequestDTO changerPasswordRequestDTO) {
        return usernamePasswordStorage.changePassword(changerPasswordRequestDTO);
    }

//    @Override
//    public User changePassword() {
//        return null;
//    }
}
