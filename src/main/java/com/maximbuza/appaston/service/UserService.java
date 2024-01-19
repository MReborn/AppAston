package com.maximbuza.appaston.service;

import java.util.Map;
import java.util.Set;

public interface UserService {
    public Set<Map.Entry<String,String>> getAllUsers();
    public void singUpUser();

    public void singInUser();

//    public User changePassword();
}
