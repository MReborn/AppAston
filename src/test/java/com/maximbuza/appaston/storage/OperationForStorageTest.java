package com.maximbuza.appaston.storage;

import com.maximbuza.appaston.dto.ChangerPasswordRequestDTO;
import com.maximbuza.appaston.dto.LoginAndRegistrationUserRequestDTO;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class OperationForStorageTest extends OperationsForStorage {
    public static ChangerPasswordRequestDTO changerPasswordRequestDTO;
    public LoginAndRegistrationUserRequestDTO userRequestDTO;

    @Before
    public void init() {
        userRequestDTO = new LoginAndRegistrationUserRequestDTO();
        changerPasswordRequestDTO = new ChangerPasswordRequestDTO();
        HashMap<String, String> userAccounts = new HashMap<>() {{
            put("Lil", "999");
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
    public void changePassword_WhenUsernameIncorrect() {
        changerPasswordRequestDTO.setUsername("");
        assertEquals(changePassword(changerPasswordRequestDTO), "Username is incorrect");
    }

    @Test
    public void changePassword_WhenUsernameNotFound() {
        changerPasswordRequestDTO.setUsername("Max");
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
        assertEquals(changePassword(changerPasswordRequestDTO), "Password was changed successfully.");
    }


    @Test
    public void signInUser_WhenUsernameIncorrect() {
        userRequestDTO.setUsername("");
        userRequestDTO.setPassword("4444");
        assertEquals(signInUser(userRequestDTO), "Username is incorrect");
    }

    @Test
    public void signInUser_WhenUsernameNotFound() {
        userRequestDTO.setUsername("Max");
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



    @Test
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
}
