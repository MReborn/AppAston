package com.maximbuza.appaston.domainService;


import com.maximbuza.appaston.dto.User;
import com.maximbuza.appaston.storage.Storage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.lang.reflect.Field;
import java.util.HashMap;

import static com.maximbuza.appaston.storage.Storage.*;
import static org.junit.Assert.*;

public class ContainerServiceTest extends ContainerService {
    @Mock
    private User user;

    @Before
    public void init() {                                                // в этом блоке меняем hashmap на кастомный с данными, используя рефлексию.
        user = new User();     // Также создаем контейнер для данных пользователя чтоб тесты были короче

        HashMap<String, String> userAccounts = new HashMap<>() {{
            put("Lil", "999");
            put("Max", "12345");
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

    @Test // 2 теста на проверку существует ли пользователь в хранилище
    public void isExistUser_ShouldTrue_WhenUserExist() {
        assertTrue(isUserExist("Max"));
    }

    @Test
    public void isExistUser_ShouldFalse_WhenUserNotExist() {
        assertFalse(isUserExist("Gena"));
    }

    @Test // тест на проверку поменялся ли пароль в хранилище на указанный
    public void setNewPassword_WhenPasswordSetInStorage() {
        setPassword("Var", "555");
        assertEquals("555", userAccounts.get("Var"));
    }

    @Test // тест метода по возвращению пароль по юзернейму из хранилища
    public void getPassword_Test() {
        assertEquals(getPasswordFromStorage("Lil"), userAccounts.get("Lil"));
    }
}
