package com.maximbuza.appaston.service;

import com.maximbuza.appaston.dto.User;

public interface UserService {
    String getAllUsers();

    String signUpUser(User user);

    String signInUser(User user);

    String changePassword(User user);
}
