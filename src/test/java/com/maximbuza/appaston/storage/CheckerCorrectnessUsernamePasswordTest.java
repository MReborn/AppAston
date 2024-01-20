package com.maximbuza.appaston.storage;

import static org.junit.Assert.*;

import org.junit.Test;

public class CheckerCorrectnessUsernamePasswordTest extends CheckerCorrectnessUsernamePassword {
    @Test
    public void isLoginIncorrect_Test() {
        assertTrue(isLoginIncorrect(""));
        assertFalse(isLoginIncorrect("Mks"));
    }

    @Test
    public void isPasswordIncorrectFormat_Test() {
        assertTrue(isPasswordIncorrectFormat(""));
        assertFalse(isPasswordIncorrectFormat("12345"));
    }




}
