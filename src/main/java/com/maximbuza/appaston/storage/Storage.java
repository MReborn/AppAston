package com.maximbuza.appaston.storage;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


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

    public static Set<Map.Entry<String, String>> giveAllUsersFromStorage() {
        return userAccounts.entrySet();
    } // достает из хранилища все юзернеймы и пароли и возвращает

    public static boolean isUserIncorrect(String username) {
        return username.equals("");
    } // проверка пустая ли строка

    public static boolean isPasswordIncorrectFormat(String password) {
        return password.equals("");
    } // проверяет пустой ли пароль и возвращает правду если пустой
}