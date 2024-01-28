package com.maximbuza.appaston.repository;

import com.maximbuza.appaston.configuration.SessionFactory;
import com.maximbuza.appaston.dto.UserDTO;
import com.maximbuza.appaston.entity.UserEntity;
import com.maximbuza.appaston.exception.NotFoundException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private final SessionFactory sessionFactory;

    public UserRepository(@Autowired SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<UserDTO> getAllUsersFromBd() { // получает список всех работников в бд.
        try (Session session = sessionFactory.getSession()) {
            List<UserEntity> userEntities = session.createQuery("from UserEntity ORDER BY id ASC", UserEntity.class).getResultList();
            if (userEntities.isEmpty()) { // если список юзеров пуст бросает 404
                throw new NotFoundException("No users in the repository");
            }
            List<UserDTO> userDtoList = new ArrayList<>();
            for (UserEntity userEntity : userEntities) {
                UserDTO user = new UserDTO();
                user.setUsername(userEntity.getUsername());
                user.setPassword(userEntity.getPassword());
                userDtoList.add(user);
            }
            return userDtoList;
        }
    }

    public void saveOrUpdateUser(String username, String password) {
        UserEntity userEntity = findByUsername(username);
        if (userEntity == null) { // если не нашел - создаст юзера и даст username
            userEntity = new UserEntity();
            userEntity.setUsername(username);
        }
        userEntity.setPassword(password);

        try (Session session = sessionFactory.getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(userEntity); // либо добавит пользователя, либо обновит
            session.flush();                  // синхронизация изменений с бд
        }
    }

    public UserEntity findByUsername(String username) { // ищет  в бд юзера по username
        try (Session session = sessionFactory.getSession()) {
            return session.createQuery("FROM UserEntity WHERE username = :username", UserEntity.class)
                    .setParameter("username", username)
                    .uniqueResult();
        }
    }

}
