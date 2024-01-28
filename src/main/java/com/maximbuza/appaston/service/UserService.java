package com.maximbuza.appaston.service;
import com.maximbuza.appaston.dto.ChangePasswordDTO;
import com.maximbuza.appaston.dto.UserDTO;

public interface UserService {
    String getAllUsers();

    String signUpUser(UserDTO user);

    String signInUser(UserDTO user);

    String changePassword(ChangePasswordDTO changePasswordDTO);
}
