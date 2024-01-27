package com.maximbuza.appaston.repository;

import com.maximbuza.appaston.configuration.SessionFactory;
import com.maximbuza.appaston.dto.User;
import com.maximbuza.appaston.entity.UserEntity;
import com.maximbuza.appaston.exception.NotFoundException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private final SessionFactory sessionFactory;

    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<User> getAllUsersFromBd() { // получает список всех работников в бд.
        try (Session session = sessionFactory.getSession()) {
            List<UserEntity> userEntities = session.createQuery("from UserEntity ORDER BY id ASC", UserEntity.class).getResultList();
            if (userEntities.isEmpty()) { // если список юзеров пуст бросает 404
                throw new NotFoundException("No users in the repository");
            }
            List<User> userDtoList = new ArrayList<>();
            for (UserEntity userEntity : userEntities) {
                User user = new User();
                user.setUsername(userEntity.getUsername());
                user.setPassword(userEntity.getPassword());
                userDtoList.add(user);
            }
            return userDtoList;
        }
    }

    public boolean isUserExist(String username) { // проверяет существование пользователя в бд по юзернейму.
        return findByUsername(username) != null;
    }
    private UserEntity findByUsername(String username) {
        try (Session session = sessionFactory.getSession()) {                  // с паролем в бд
            return session.createQuery("FROM UserEntity WHERE username = :username", UserEntity.class)
                    .setParameter("username", username)
                    .uniqueResult();
        }
    }

    public boolean isPasswordMatch(String username, String passwordPossible) { // проверка на совпадение указанного пароля в параметрах
        UserEntity userEntity = findByUsername(username);
        return userEntity.getPassword().equals(passwordPossible);
    }

    public void saveOrUpdateUser(User user) {
        UserEntity userEntity = findByUsername(user.getUsername());
        if (userEntity == null) { // если не нашел - создаст юзера
            userEntity = new UserEntity();
            userEntity.setUsername(user.getUsername());
            userEntity.setPassword(user.getPassword());
        } else { // если нашел - значит обновляем пароль
            userEntity.setPassword(user.getNewPassword());
        }
        try (Session session = sessionFactory.getSession()) {               // если username уже такой есть, то обновит пароль
            session.beginTransaction();
            session.saveOrUpdate(userEntity); // либо добавит пользователя, либо обновит
            session.flush();
            // синхронизация изменений с бд
        }
    }


}
