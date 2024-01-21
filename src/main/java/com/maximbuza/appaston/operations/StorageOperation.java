package com.maximbuza.appaston.operations;

import com.maximbuza.appaston.dto.ChangerPasswordRequestDTO;
import com.maximbuza.appaston.dto.LoginAndRegistrationUserRequestDTO;

public interface StorageOperation {
    String giveAllUser();

    String signUpUser(LoginAndRegistrationUserRequestDTO user);

    String signInUser(LoginAndRegistrationUserRequestDTO user);

    String changePassword(ChangerPasswordRequestDTO changerPasswordRequestDTO);


}
