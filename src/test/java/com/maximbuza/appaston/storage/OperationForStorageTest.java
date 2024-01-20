package com.maximbuza.appaston.storage;

import com.maximbuza.appaston.dto.ChangerPasswordRequestDTO;
import com.maximbuza.appaston.dto.LoginAndRegistrationUserRequestDTO;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class OperationForStorageTest  extends OperationsForStorage{
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
    public void changePasswordTest() {
        changerPasswordRequestDTO.setUsername("");
        assertEquals(changePassword(changerPasswordRequestDTO), "Login is incorrect");

        changerPasswordRequestDTO.setUsername("Max");
        assertEquals(changePassword(changerPasswordRequestDTO), "The user was not found");

        changerPasswordRequestDTO.setUsername("Lil");
        changerPasswordRequestDTO.setOldPassword("");
        changerPasswordRequestDTO.setNewPassword("2222");
        assertEquals(changePassword(changerPasswordRequestDTO), "Some of the Passwords in the wrong format :(");

        changerPasswordRequestDTO.setOldPassword("3333");
        changerPasswordRequestDTO.setNewPassword("");
        assertEquals(changePassword(changerPasswordRequestDTO), "Some of the Passwords in the wrong format :(");

        changerPasswordRequestDTO.setUsername("Lil");
        changerPasswordRequestDTO.setOldPassword("99");
        changerPasswordRequestDTO.setNewPassword("2222");
        assertEquals(changePassword(changerPasswordRequestDTO), "Wrong password");

        changerPasswordRequestDTO.setOldPassword("999");
        assertEquals(changePassword(changerPasswordRequestDTO), "Password was changed successfully.");
    }

    @Test
    public void signInUserTest(){
        userRequestDTO.setUsername("");
        userRequestDTO.setPassword("4444");
        assertEquals(signInUser(userRequestDTO),"Login is incorrect");

        userRequestDTO.setUsername("Max");
        assertEquals(signInUser(userRequestDTO),"The user was not found");

        userRequestDTO.setUsername("Lil");
        userRequestDTO.setPassword("");
        assertEquals(signInUser(userRequestDTO),"Password is incorrect format:(");

        userRequestDTO.setPassword("1999");
        assertEquals(signInUser(userRequestDTO),"Wrong password");

        userRequestDTO.setPassword("999");
        assertEquals(signInUser(userRequestDTO),"Successful login. Congratulations");
    }

    @Test
    public void signUpUserTest(){
        userRequestDTO.setUsername("");
        assertEquals(signUpUser(userRequestDTO),"Login is incorrect format");

        userRequestDTO.setUsername("Lil");
        assertEquals(signUpUser(userRequestDTO),"Oh no! The user has already been added once");

        userRequestDTO.setUsername("Mks");
        userRequestDTO.setPassword("");
        assertEquals(signUpUser(userRequestDTO),"Password is incorrect format :(");

        userRequestDTO.setPassword("321");
        assertEquals(signUpUser(userRequestDTO),"User has been added:\nlogin: " + userRequestDTO.getUsername() + "\npassword: " + userRequestDTO.getPassword());

    }
}
