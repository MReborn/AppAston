package com.maximbuza.appaston.storage;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
@Repository
public class Storage {
    public static HashMap<String, String> userAccounts = new HashMap<>(); // здесь хранятся все данные о пользователях
}