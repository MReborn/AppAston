package com.maximbuza.appaston.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {
    private String username;
    private String password;

    @Override
    public String toString() {
        return "username='" + username + '\'' +
                ", password='" + password;
    }
}
