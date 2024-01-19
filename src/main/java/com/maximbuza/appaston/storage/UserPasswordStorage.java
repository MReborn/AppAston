package com.maximbuza.appaston.storage;

import com.maximbuza.appaston.dto.LoginAndRegistrationUserRequestDTO;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class UserPasswordStorage {
    public String signUpUser(LoginAndRegistrationUserRequestDTO user) {
        String loginPossible = user.getUsername();
        String passwordPossible = user.getPassword();
        if (isLoginCorrect(loginPossible)) {
            return "Login is incorrect format";
        }
        if (!isUserExist(loginPossible)) {
            if (isPasswordCorrect(passwordPossible)) {
                return "Password is incorrect format :(";
            }
            userAccounts.put(loginPossible, passwordPossible);
            return "User has been added:\n login: " + loginPossible + "\npassword: " + passwordPossible;
        } else {
            return "Oh no! The user has already been added once";
        }

    }

    public String signInUser(LoginAndRegistrationUserRequestDTO user) {
        String login = user.getUsername();
        String password = user.getPassword();

        if (isLoginCorrect(login)) {
            return "Login is incorrect";
        }

        if (isUserExist(login)) {
            if (isPasswordCorrect(password)) {
                return "Password is incorrect format:(";
            } else if (isPasswordMatch(login, password)) {
                return "Successful login. Congratulations";
            } else return "Wrong password";

        } else return "The user was not found";
    }


    public static boolean isLoginCorrect(String login) {
        return login.equals("");
    }

    public static boolean isPasswordCorrect(String password) {
        return password.equals("");
    }

    public static boolean isUserExist(String username) {
        return userAccounts.containsKey(username);
    }

    public static boolean isPasswordMatch(String username, String password) {
        return userAccounts.get(username).equals(password);
    }

    public Set<Map.Entry<String, String>> giveAllUser() {
        return userAccounts.entrySet();
    }

    static HashMap<String, String> userAccounts = new HashMap<>() {{
        put("Max", "12345");
        put("Boot", "11111");
        put("Var", "54321");
    }};

}