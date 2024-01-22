package com.maximbuza.appaston.dto;

public class SignInAndUpRequestDTO {
    private String username;
    private String password;

    public String getUsernameSignInAndUpDTO() {
        return username;
    }

    public String getPasswordSignInAndUpDTO() {
        return password;
    }

    public void setUsernameSignInAndUpDTO(String username) {
        this.username = username;
    }

    public void setPasswordSignInAndUpDTO(String password) {
        this.password = password;
    }
}
