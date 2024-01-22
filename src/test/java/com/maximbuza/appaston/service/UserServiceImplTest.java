package com.maximbuza.appaston.service;

import com.maximbuza.appaston.dto.ChangerPasswordRequestDTO;
import com.maximbuza.appaston.dto.SignInAndUpRequestDTO;
import com.maximbuza.appaston.storage.Storage;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserServiceImplTest extends UserServiceImpl {
    public ChangerPasswordRequestDTO changerPasswordRequestDTO;
    public SignInAndUpRequestDTO userRequestDTO;

    @Before
    public void init() {                                                // в этом блоке меняем hashmap на кастомный с данными, используя рефлексию.
        userRequestDTO = new SignInAndUpRequestDTO();      // Также создаем контейнеры для данных пользователя чтоб тесты были короче
        changerPasswordRequestDTO = new ChangerPasswordRequestDTO();
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
        changerPasswordRequestDTO.setUsername("");
        assertEquals(changePassword(changerPasswordRequestDTO), "Username is incorrect");
    }

    @Test
    public void changePassword_WhenUsernameNotFound() {
        changerPasswordRequestDTO.setUsername("Kira");
        assertEquals(changePassword(changerPasswordRequestDTO), "The user was not found");
    }

    @Test
    public void changePassword_WhenOldPasswordIncorrect() {
        changerPasswordRequestDTO.setUsername("Lil");
        changerPasswordRequestDTO.setOldPassword("");
        changerPasswordRequestDTO.setNewPassword("2222");
        assertEquals(changePassword(changerPasswordRequestDTO), "Some of the Passwords in the wrong format :(");
    }

    @Test
    public void changePassword_WhenNewPasswordIncorrect() {
        changerPasswordRequestDTO.setUsername("Lil");
        changerPasswordRequestDTO.setOldPassword("3333");
        changerPasswordRequestDTO.setNewPassword("");
        assertEquals(changePassword(changerPasswordRequestDTO), "Some of the Passwords in the wrong format :(");
    }

    @Test
    public void changePassword_WhenPasswordIsWrong() {
        changerPasswordRequestDTO.setUsername("Lil");
        changerPasswordRequestDTO.setOldPassword("99");
        changerPasswordRequestDTO.setNewPassword("2222");
        assertEquals(changePassword(changerPasswordRequestDTO), "Wrong password");
    }

    @Test
    public void changePassword_WhenPasswordWasChanged() {
        changerPasswordRequestDTO.setUsername("Lil");
        changerPasswordRequestDTO.setOldPassword("999");
        changerPasswordRequestDTO.setNewPassword("2222");
        assertEquals(changePassword(changerPasswordRequestDTO), "Password was changed successfully. Your new login details:\nusername: " +
                changerPasswordRequestDTO.getUsernameChangerDTO() + "\npassword: " + changerPasswordRequestDTO.getNewPassword())
        ;
    }


    @Test // 5 тестов сервиса по входу юзера
    public void signInUser_WhenUsernameIncorrect() {
        userRequestDTO.setUsername("");
        userRequestDTO.setPassword("4444");
        assertEquals(signInUser(userRequestDTO), "Username is incorrect");
    }

    @Test
    public void signInUser_WhenUsernameNotFound() {
        userRequestDTO.setUsername("Kira");
        userRequestDTO.setPassword("4444");
        assertEquals(signInUser(userRequestDTO), "The user was not found");
    }

    @Test
    public void signInUser_WhenPasswordIncorrect() {
        userRequestDTO.setUsername("Lil");
        userRequestDTO.setPassword("");
        assertEquals(signInUser(userRequestDTO), "Password is incorrect format:(");
    }

    @Test
    public void signInUser_WhenPasswordIsWrong() {
        userRequestDTO.setUsername("Lil");
        userRequestDTO.setPassword("1999");
        assertEquals(signInUser(userRequestDTO), "Wrong password");
    }

    @Test
    public void signInUser_WhenAllCorrect() {
        userRequestDTO.setUsername("Lil");
        userRequestDTO.setPassword("999");
        assertEquals(signInUser(userRequestDTO), "Successful login. Congratulations");
    }


    @Test // 5 тестов сервиса регистрации юзера
    public void signUpUser_WhenUsernameIncorrect() {
        userRequestDTO.setUsername("");
        userRequestDTO.setPassword("123");
        assertEquals(signUpUser(userRequestDTO), "Username is incorrect format");
    }

    @Test
    public void signUpUser_WhenUserHasAlreadyAdded() {
        userRequestDTO.setUsername("Lil");
        userRequestDTO.setPassword("123");
        assertEquals(signUpUser(userRequestDTO), "Oh no! The user has already been added once");
    }

    @Test
    public void signUpUser_WhenPasswordIncorrect() {
        userRequestDTO.setUsername("Mks");
        userRequestDTO.setPassword("");
        assertEquals(signUpUser(userRequestDTO), "Password is incorrect format :(");
    }

    @Test
    public void signUpUser_WhenUserHasBeenAdded() {
        userRequestDTO.setUsername("Mks");
        userRequestDTO.setPassword("321");
        assertEquals(signUpUser(userRequestDTO), "User has been added:\nlogin: " + userRequestDTO.getUsername() + "\npassword: " + userRequestDTO.getPassword());

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
