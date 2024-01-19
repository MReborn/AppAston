package com.maximbuza.appaston.service;

import com.maximbuza.appaston.storage.UserPasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    public UserServiceImpl(@Autowired UserPasswordStorage userPasswordStorage) {
        this.userPasswordStorage = userPasswordStorage;
    }
    private UserPasswordStorage userPasswordStorage;


    @Override
    public Set<Map.Entry<String,String>> getAllUsers() {
        return userPasswordStorage.giveAllUser();
    }

    @Override
    public void singUpUser() {

    }

    @Override
    public void singInUser() {

    }

//    @Override
//    public User changePassword() {
//        return null;
//    }
}
