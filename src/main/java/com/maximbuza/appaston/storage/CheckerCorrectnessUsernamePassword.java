package com.maximbuza.appaston.storage;
public class CheckerCorrectnessUsernamePassword {

    public static boolean isLoginIncorrect(String login) {
        return login.equals("");
    }

    public static boolean isPasswordIncorrectFormat(String password) {
        return password.equals("");
    }



}
