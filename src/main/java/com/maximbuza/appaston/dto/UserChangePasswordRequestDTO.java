package com.maximbuza.appaston.dto;

public class UserChangePasswordRequestDTO {
    private String username;
    private String oldPassword;

    private String newPassword;

    public String getUsername() {
        return username;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
