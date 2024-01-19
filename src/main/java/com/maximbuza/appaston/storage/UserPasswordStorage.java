package com.maximbuza.appaston.storage;

import com.maximbuza.appaston.dto.UserRegistrationRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class UserPasswordStorage {

    HashMap<String, String> userAccounts = new HashMap<>() {{
        put("Max", "12345");
        put("Boot", "11111");
        put("Var", "54321");
    }};


    public boolean isUserExist(String username) {
        return userAccounts.containsKey(username);
    }

    public boolean isPasswordCorrect(String username, String password) {
        return userAccounts.get(username).equals(password);
    }

    public Set<Map.Entry<String, String>> giveAllUser() {
        return userAccounts.entrySet();
    }


    public String signUpUser(UserRegistrationRequestDTO userRegistrationRequestDTO) {
        String userPossible = userRegistrationRequestDTO.getUsername();
        String passwordPossible = userRegistrationRequestDTO.getPassword();
        if(userPossible.equals("")){return "Login is incorrect";}
        if (!this.isUserExist(userPossible)) {
            if(passwordPossible.equals("")){return "Password is incorrect :(";}
            userAccounts.put(userPossible, passwordPossible);
            return "User has been added:\n login: "+userPossible+"\npassword: "+passwordPossible;
        } else {
            return "Oh no! The user has already been added once";
        }

    }
}