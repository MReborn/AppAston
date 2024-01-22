package com.maximbuza.appaston.service;


import com.maximbuza.appaston.dto.User;
import com.maximbuza.appaston.storage.Storage;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.lang.reflect.Field;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserServiceImplTest extends UserServiceImpl {

    @Mock
    private User user;

    @Before
    public void init() {    // в этом блоке меняем hashmap на кастомный с данными, используя рефлексию.
        user = new User();
        HashMap<String, String> userAccounts = new HashMap<>() {{
            put("Lil", "999");
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


    @Test // 6 тестов сервиса смены пароля
    public void changePassword_WhenUsernameIncorrect() {
        user.setUsername("");
        assertEquals(changePassword(user), "Username is incorrect");
    }

    @Test
    public void changePassword_WhenUsernameNotFound() {
        user.setUsername("Kira");
        assertEquals(changePassword(user), "The user was not found");
    }

    @Test
    public void changePassword_WhenOldPasswordIncorrect() {
        user.setUsername("Lil");
        user.setPassword("");
        user.setNewPassword("2222");
        assertEquals(changePassword(user), "Some of the Passwords in the wrong format :(");
    }

    @Test
    public void changePassword_WhenNewPasswordIncorrect() {
        user.setUsername("Lil");
        user.setPassword("3333");
        user.setNewPassword("");
        assertEquals(changePassword(user), "Some of the Passwords in the wrong format :(");
    }

    @Test
    public void changePassword_WhenPasswordIsWrong() {
        user.setUsername("Lil");
        user.setPassword("99");
        user.setNewPassword("2222");
        assertEquals(changePassword(user), "Wrong password");
    }

    @Test
    public void changePassword_WhenPasswordWasChanged() {
        user.setUsername("Lil");
        user.setPassword("999");
        user.setNewPassword("2222");
        assertEquals(changePassword(user), "Password was changed successfully. Your new login details:\nusername: " +
                user.getUsername() + "\npassword: " + user.getNewPassword())
        ;
    }


    @Test // 5 тестов сервиса по входу юзера
    public void signInUser_WhenUsernameIncorrect() {
        user.setUsername("");
        user.setPassword("4444");
        assertEquals(signInUser(user), "Username is incorrect");
    }

    @Test
    public void signInUser_WhenUsernameNotFound() {
        user.setUsername("Kira");
        user.setPassword("4444");
        assertEquals(signInUser(user), "The user was not found");
    }

    @Test
    public void signInUser_WhenPasswordIncorrect() {
        user.setUsername("Lil");
        user.setPassword("");
        assertEquals(signInUser(user), "Password is incorrect format:(");
    }

    @Test
    public void signInUser_WhenPasswordIsWrong() {
        user.setUsername("Lil");
        user.setPassword("1999");
        assertEquals(signInUser(user), "Wrong password");
    }

    @Test
    public void signInUser_WhenAllCorrect() {
        user.setUsername("Lil");
        user.setPassword("999");
        assertEquals(signInUser(user), "Successful login. Congratulations");
    }


    @Test // 5 тестов сервиса регистрации юзера
    public void signUpUser_WhenUsernameIncorrect() {
        user.setUsername("");
        user.setPassword("123");
        assertEquals(signUpUser(user), "Username is incorrect format");
    }

    @Test
    public void signUpUser_WhenUserHasAlreadyAdded() {
        user.setUsername("Lil");
        user.setPassword("123");
        assertEquals(signUpUser(user), "Oh no! The user has already been added once");
    }

    @Test
    public void signUpUser_WhenPasswordIncorrect() {
        user.setUsername("Mks");
        user.setPassword("");
        assertEquals(signUpUser(user), "Password is incorrect format :(");
    }

    @Test
    public void signUpUser_WhenUserHasBeenAdded() {
        user.setUsername("Mks");
        user.setPassword("321");
        assertEquals(signUpUser(user), "User has been added:\nlogin: " + user.getUsername() + "\npassword: " + user.getPassword());

    }

    @Test // 2 теста метода по проверке корректности юзернейма
    public void isUsernameIncorrect_ShouldBeTrue_WhenUserEmpty() {
        assertTrue(isUserIncorrect(""));
    }

    @Test
    public void isUsernameIncorrect_ShouldBeFalse_WhenUserNotEmpty() {
        assertFalse(isUserIncorrect("Mks"));
    }


    @Test // 2 теста метода по проверке корректности пароля
    public void isPasswordIncorrectFormat_ShouldBeTrue_WhenPasswordEmpty() {
        assertTrue(isPasswordIncorrectFormat(""));
    }

    @Test
    public void isPasswordIncorrectFormat_ShouldBeFalse_WhenPasswordNotEmpty() {
        assertFalse(isPasswordIncorrectFormat("12345"));
    }

    @Test // 2 теста метода по проверки совпадения пароля с хранилищем
    public void isPasswordMatch_ShouldFalse_WhenPasswordNotMatch() {
        assertFalse(isPasswordMatch("Max", "00000"));
    }

    @Test
    public void isPasswordMatch_ShouldTrue_WhenPasswordMatch() {
        assertTrue(isPasswordMatch("Boot", "111112"));
    }
}
