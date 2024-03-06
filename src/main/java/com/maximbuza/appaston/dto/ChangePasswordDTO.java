package com.maximbuza.appaston.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChangePasswordDTO { // нужен для смены пароля пользователя
    private String username;
    private String oldPassword;
    private String newPassword;
}
