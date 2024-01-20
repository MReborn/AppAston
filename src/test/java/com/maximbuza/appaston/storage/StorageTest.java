package com.maximbuza.appaston.storage;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.HashMap;

import static org.junit.Assert.*;

public class StorageTest extends Storage {
    @Before
    public void init() {
        HashMap<String, String> userAccounts = new HashMap<>() {{
            put("Max", "12345");
            put("Boot", "111112");
            put("Var", "54321");
        }};
        Field field;
        try {
            field = Storage.class.getDeclaredField("userAccounts");
            field.setAccessible(true);
            field.set(Storage.class, userAccounts);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void isExistUser_Test() {
        assertTrue(isUserExist("Max"));
        assertFalse(isUserExist("Gena"));
    }

    @Test
    public void isPasswordMatch_Test() {
        assertFalse(isPasswordMatch("Max", "00000"));
        assertTrue(isPasswordMatch("Boot", "111112"));
    }

    @Test
    public void setNewPassword() {
        setNewPassword("Var","555");
        assertEquals("555",userAccounts.get("Var"));

    }

}
