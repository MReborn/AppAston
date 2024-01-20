package com.maximbuza.appaston.storage;

import java.util.HashMap;


public class Storage {
    static HashMap<String, String> userAccounts = new HashMap<>();


    public static boolean isPasswordMatch(String username, String password) {

        return userAccounts.get(username).equals(password);
    }


    public static void setNewPassword(String username, String newPassword) {
        userAccounts.put(username, newPassword);
    }


    public static boolean isUserExist(String username) {
        return userAccounts.containsKey(username);
    }
}