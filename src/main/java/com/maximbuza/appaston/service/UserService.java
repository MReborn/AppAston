package com.maximbuza.appaston.service;

import com.maximbuza.appaston.dto.UserRegistrationRequestDTO;

import java.util.Map;
import java.util.Set;

public interface UserService {
    public Set<Map.Entry<String,String>> getAllUsers();
    public String singUpUser(UserRegistrationRequestDTO userRegistrationRequestDTO);

    public void singInUser();

//    public User changePassword();
}
