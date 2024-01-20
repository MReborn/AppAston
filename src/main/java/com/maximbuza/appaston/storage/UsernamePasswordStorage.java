package com.maximbuza.appaston.storage;

import static com.maximbuza.appaston.storage.CheckerUsernamePasswordForStorage.*;

import com.maximbuza.appaston.dto.LoginAndRegistrationUserRequestDTO;
import com.maximbuza.appaston.dto.ChangerPasswordRequestDTO;
import org.springframework.stereotype.Component;

import java.util.HashMap;


@Component
public class UsernamePasswordStorage {
    static HashMap<String, String> userAccounts = new HashMap<>() {{
        put("Max", "12345");
        put("Boot", "11111");
        put("Var", "54321");
    }};

    public String giveAllUser() {
        return "List of user usernames and passwords:\n" + userAccounts.entrySet();
    }

    public String signUpUser(LoginAndRegistrationUserRequestDTO user) {
        String loginPossible = user.getUsername();
        String passwordPossible = user.getPassword();
        if (isLoginIncorrect(loginPossible)) {
            return "Login is incorrect format";
        }
        if (isUserExist(loginPossible)) {
            return "Oh no! The user has already been added once";
        }
        if (isPasswordIncorrectFormat(passwordPossible)) {
            return "Password is incorrect format :(";
        }
        userAccounts.put(loginPossible, passwordPossible);
        return "User has been added:\nlogin: " + loginPossible + "\npassword: " + passwordPossible;
    }


    public String signInUser(LoginAndRegistrationUserRequestDTO user) {
        String username = user.getUsername();
        String password = user.getPassword();

        if (isLoginIncorrect(username)) {
            return "Login is incorrect";
        }

        if (isUserExist(username)) {
            if (isPasswordIncorrectFormat(password)) {
                return "Password is incorrect format:(";
            } else if (isPasswordMatch(username, password)) {
                return "Successful login. Congratulations";
            } else return "Wrong password";

        } else return "The user was not found";
    }

    public String changePassword(ChangerPasswordRequestDTO changerPasswordRequestDTO) {
        String username = changerPasswordRequestDTO.getUsername();
        String oldPassword = changerPasswordRequestDTO.getOldPassword();
        String newPassword = changerPasswordRequestDTO.getNewPassword();

        if (isLoginIncorrect(username)) {
            return "Login is incorrect";
        }
        if (!isUserExist(username)) {
            return "The user was not found";
        }
        if (isPasswordIncorrectFormat(oldPassword) || isPasswordIncorrectFormat(newPassword)) {
            return "Some of the Passwords in the wrong format :(";
        }
        if (isPasswordMatch(username, oldPassword)) {
            setNewPassword(username, newPassword);
            return "Password was changed successfully.";
        } else {
            return "Wrong password";
        }
    }
}