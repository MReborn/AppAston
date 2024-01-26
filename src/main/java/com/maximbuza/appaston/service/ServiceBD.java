package com.maximbuza.appaston.service;

import com.maximbuza.appaston.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ServiceBD {
    public ServiceBD(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;

    public String getAllUsersFromBd() {
        return "List of user usernames and passwords:\n" + userRepository.getAllUsersFromBd();
    } // просит вернуть всех юзеров класс, работающий с хранилищем
}
