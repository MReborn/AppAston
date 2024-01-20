package com.maximbuza.appaston.storage;
import static com.maximbuza.appaston.storage.UsernamePasswordStorage.*;
public class CheckerUsernamePasswordForStorage {
    public static boolean isLoginIncorrect(String login) {
        return login.equals("");
    }

    public static boolean isPasswordIncorrectFormat(String password) {
        return password.equals("");
    }
    public static boolean isUserExist(String username) {
        return userAccounts.containsKey(username);
    }
    public static boolean isPasswordMatch(String username, String password) {

        return userAccounts.get(username).equals(password);
    }

    public static void setNewPassword(String username, String newPassword) {
        userAccounts.put(username,newPassword);
    }


}
