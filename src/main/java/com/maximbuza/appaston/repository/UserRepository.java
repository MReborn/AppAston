package com.maximbuza.appaston.repository;

import com.maximbuza.appaston.configuration.SessionFactory;
import com.maximbuza.appaston.dto.User;
import com.maximbuza.appaston.entity.UserEntity;
import com.maximbuza.appaston.exception.NotFoundException;
import org.hibernate.Session;
import org.hibernate.query.Query;
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
        try (Session session = sessionFactory.getSession()) {
            String hql = "SELECT COUNT(*) FROM UserEntity WHERE username = :username"; // считает количество пользователей с юзернеймом в параметрах
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("username", username);
            Long count = query.uniqueResult();
            return count > 0; // если количество больше 0 то пользователь в бд существует
        }
    }


    public void addUserOrUpdatePassword(String username, String password) { // если пользователя нет в бд, то добавит нового,
        try (Session session = sessionFactory.getSession()) {               // если username уже такой есть, то обновит пароль
            session.beginTransaction();
            UserEntity userEntity = session.createQuery("FROM UserEntity WHERE username = :username", UserEntity.class)
                    .setParameter("username", username)
                    .uniqueResult();
            if (userEntity == null) { // если не нашел - создаст юзера
                userEntity = new UserEntity();
                userEntity.setUsername(username);
            }
            userEntity.setPassword(password);

            session.saveOrUpdate(userEntity); // либо добавит пользователя, либо обновит
            session.flush();                  // синхронизация изменений с бд
        }
    }


    public boolean isPasswordMatch(String username, String passwordPossible) { // проверка на совпадение указанного пароля в параметрах
        try (Session session = sessionFactory.getSession()) {                  // с паролем в бд
            UserEntity userEntity = session.createQuery("FROM UserEntity WHERE username = :username", UserEntity.class)
                    .setParameter("username", username)
                    .uniqueResult();
            return userEntity.getPassword().equals(passwordPossible);
        }
    }
}
