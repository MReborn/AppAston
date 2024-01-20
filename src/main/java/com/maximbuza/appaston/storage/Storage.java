package com.maximbuza.appaston.storage;

import java.util.HashMap;


public class Storage {
    static HashMap<String, String> userAccounts = new HashMap<>(); // здесь хранятся все данные о пользователях


    public static boolean isPasswordMatch(String username, String password) { // метод проверяет совпадают ли пароль в хранилище с переданным паролем

        return userAccounts.get(username).equals(password); // подтягивает через юзернейм и сравнивает с параметром
    }


    public static void setPassword(String username, String newPassword) {
        userAccounts.put(username, newPassword);
    } // метод меняет пароль по юзернейму на новый пароль из параметров


    public static boolean isUserExist(String username) {
        return userAccounts.containsKey(username);
    } // существует ли пользователь в хранилище. Сверяет с ключами
}