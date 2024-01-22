package com.maximbuza.appaston.domainService;

import java.util.Map;
import java.util.Set;

import static com.maximbuza.appaston.storage.Storage.userAccounts;

public class ContainerService {
    public static void setPassword(String username, String newPassword) {
        userAccounts.put(username, newPassword);
    } // метод меняет пароль по юзернейму на новый пароль из параметров

    public static Set<Map.Entry<String, String>> giveAllUsersFromStorage() {
        return userAccounts.entrySet();
    } // достает из хранилища все юзернеймы и пароли и возвращает

    public static boolean isUserExist(String username) {
        return userAccounts.containsKey(username);
    } // существует ли пользователь в хранилище. Сверяет с ключами

    public static String getPasswordFromStorage(String username) { //получает пароль из хранилища по username
        return userAccounts.get(username);
    }

}