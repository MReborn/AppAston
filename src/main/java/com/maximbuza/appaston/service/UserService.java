package com.maximbuza.appaston.service;

import com.maximbuza.appaston.dto.LoginAndRegistrationUserRequestDTO;
import com.maximbuza.appaston.dto.ChangerPasswordRequestDTO;

import java.util.Map;
import java.util.Set;

public interface UserService {
    public String getAllUsers();

    public String singUpUser(LoginAndRegistrationUserRequestDTO loginAndRegistrationUserRequestDTO);

    public String singInUser(LoginAndRegistrationUserRequestDTO loginAndRegistrationUserRequestDTO);

    public String changePassword(ChangerPasswordRequestDTO changerPasswordRequestDTO);
}
