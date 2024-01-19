package com.maximbuza.appaston.storage;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
@Component
public class UserPasswordStorage {
    HashMap<String, String> userAccount = new HashMap<>(){{put("Max","12345");put("Boot","11111");put("Var","54321");}};

    public boolean isUserExist(String username) {
        return userAccount.containsKey(username);
    }

    public boolean isPasswordCorrect(String username, String password) {
        return userAccount.get(username).equals(password);
    }

    public Set<Map.Entry<String,String>> giveAllUser(){
        return userAccount.entrySet();
    }
}
