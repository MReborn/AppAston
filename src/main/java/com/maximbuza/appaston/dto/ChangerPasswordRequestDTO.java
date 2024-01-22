package com.maximbuza.appaston.dto;

public class ChangerPasswordRequestDTO {
    private String username;
    private String oldPassword;
    private String newPassword;

    public String getUsernameChangerDTO() {
        return username;
    }

    public String getOldPasswordChangerDTO() {
        return oldPassword;
    }

    public String getNewPasswordChangerDTO() {
        return newPassword;
    }

    public void setUsernameChangerDTO(String username) {
        this.username = username;
    }

    public void setOldPasswordChangerDTO(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public void setNewPasswordChangerDTO(String newPassword) {
        this.newPassword = newPassword;
    }
}

