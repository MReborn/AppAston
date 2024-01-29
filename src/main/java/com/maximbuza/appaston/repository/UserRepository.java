package com.maximbuza.appaston.repository;

import com.maximbuza.appaston.configuration.SessionFactory;
import com.maximbuza.appaston.dto.UserDTO;
import com.maximbuza.appaston.entity.UserEntity;
import com.maximbuza.appaston.exception.DatabaseException;
import com.maximbuza.appaston.exception.NotFoundException;
import org.hibernate.HibernateException;
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

    public List<UserEntity> getAllUsersFromBd() { // получает список всех работников в бд.
        List<UserEntity> userEntities;
        try (Session session = sessionFactory.getSession()) {
            userEntities = session.createQuery("FROM UserEntity ORDER BY id ASC", UserEntity.class).getResultList();
            if (userEntities.isEmpty()) { // если список юзеров пуст бросает 404
                throw new NotFoundException("No users in the repository");
            }
        } catch (HibernateException e) {
            throw new DatabaseException("Error retrieving users from the database");
        }

        return userEntities;
    }


    public void saveOrUpdateUser(String username, String password) {
        UserEntity userEntity = findByUsername(username);
        if (userEntity == null) { // если не нашел - создаст юзера и даст username
            userEntity = new UserEntity();
            userEntity.setUsername(username);
        }
        userEntity.setPassword(password); // добавление/смена пароля

        try (Session session = sessionFactory.getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(userEntity); // либо добавит пользователя, либо обновит
            session.flush();                  // синхронизация изменений с бд
        } catch (HibernateException e) {
            throw new DatabaseException("Error saving/updating the user to the database");
        }
    }

    public UserEntity findByUsername(String username) { // ищет  в бд юзера по username
        try (Session session = sessionFactory.getSession()) {
            return session.createQuery("FROM UserEntity WHERE username = :username", UserEntity.class)
                    .setParameter("username", username)
                    .uniqueResult();
        } catch (HibernateException e) {
            throw new DatabaseException("User search error in the database");
        }
    }
}
