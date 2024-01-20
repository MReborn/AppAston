package com.maximbuza.appaston.storage;

import static org.junit.Assert.*;

import org.junit.Test;

public class CheckerCorrectnessUsernamePasswordTest extends CheckerCorrectnessUsernamePassword {
    @Test
    public void isLoginIncorrect_ShouldBeTrue_WhenUserEmpty() {
        assertTrue(isUserIncorrect(""));
    }
    @Test
    public void isLoginIncorrect_ShouldBeFalse_WhenUserNotEmpty() {
        assertFalse(isUserIncorrect("Mks"));
    }

    @Test
    public void isPasswordIncorrectFormat_ShouldBeTrue_WhenPasswordEmpty() {
        assertTrue(isPasswordIncorrectFormat(""));
    }
    @Test
    public void isPasswordIncorrectFormat_ShouldBeFalse_WhenPasswordNotEmpty() {
        assertFalse(isPasswordIncorrectFormat("12345"));
    }




}
