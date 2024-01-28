package com.maximbuza.appaston.service;

import com.maximbuza.appaston.dto.ChangePasswordDTO;
import com.maximbuza.appaston.dto.UserDTO;

public interface UserService {
    String getAllUsers();

    String signUpUser(UserDTO user);


    boolean isUserExist(String username);


    String signInUser(UserDTO user);

    boolean isPasswordMatch(String username, String passwordPossible);

    String changePassword(ChangePasswordDTO changePasswordDTO);
}
