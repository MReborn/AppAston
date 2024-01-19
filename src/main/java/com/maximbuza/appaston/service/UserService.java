package com.maximbuza.appaston.service;

import com.maximbuza.appaston.dto.LoginAndRegistrationUserRequestDTO;

import java.util.Map;
import java.util.Set;

public interface UserService {
    public Set<Map.Entry<String,String>> getAllUsers();
    public String singUpUser(LoginAndRegistrationUserRequestDTO loginAndRegistrationUserRequestDTO);

    public String singInUser(LoginAndRegistrationUserRequestDTO loginAndRegistrationUserRequestDTO);

//    public User changePassword();
}
