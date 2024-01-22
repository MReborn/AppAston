package com.maximbuza.appaston.service;

import com.maximbuza.appaston.dto.SignInAndUpRequestDTO;
import com.maximbuza.appaston.dto.ChangerPasswordRequestDTO;

public interface UserService {
    String getAllUsers();

    String signUpUser(SignInAndUpRequestDTO signInSignUpRequestDTO);

    String signInUser(SignInAndUpRequestDTO signInSignUpRequestDTO);

    String changePassword(ChangerPasswordRequestDTO changerPasswordRequestDTO);
}
