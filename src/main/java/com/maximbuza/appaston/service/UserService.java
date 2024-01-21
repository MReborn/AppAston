package com.maximbuza.appaston.service;

import com.maximbuza.appaston.dto.LoginAndRegistrationUserRequestDTO;
import com.maximbuza.appaston.dto.ChangerPasswordRequestDTO;

public interface UserService {
    String getAllUsers();

    String singUpUser(LoginAndRegistrationUserRequestDTO loginAndRegistrationUserRequestDTO);

    String singInUser(LoginAndRegistrationUserRequestDTO loginAndRegistrationUserRequestDTO);

    String changePassword(ChangerPasswordRequestDTO changerPasswordRequestDTO);
}
