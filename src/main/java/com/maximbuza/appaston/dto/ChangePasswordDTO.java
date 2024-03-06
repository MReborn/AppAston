package com.maximbuza.appaston.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO для смены пароля пользователя.
 */
@Setter
@Getter
public class ChangePasswordDTO {
    private String username;
    private String oldPassword;
    private String newPassword;
}
