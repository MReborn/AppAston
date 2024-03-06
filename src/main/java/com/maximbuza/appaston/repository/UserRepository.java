package com.maximbuza.appaston.repository;

import com.maximbuza.appaston.configuration.SessionFactory;
import com.maximbuza.appaston.entity.UserEntity;
import com.maximbuza.appaston.exception.DatabaseException;
import com.maximbuza.appaston.exception.NotFoundException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import java.util.List;

@Repository
public class UserRepository {
    private final SessionFactory sessionFactory;

    public UserRepository(@Autowired SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private static final String NOT_FOUND_EX_MESSAGE = "No users in the repository";
    private static final String DATABASE_GET_EX_MESSAGE = "Error retrieving users from the database";
    private static final String DATABASE_SAVE_EX_MESSAGE = "Error saving/updating the user to the database";

    /**
     * Метод для получения списка всех пользователей.
     * @return {@link List}<{@link UserEntity}>
     */
    public List<UserEntity> getAllUsersFromBd() {
        List<UserEntity> userEntities;
        try (Session session = sessionFactory.getSession()) {
            userEntities = session.createQuery("FROM UserEntity ORDER BY id ASC", UserEntity.class).getResultList();
            if (userEntities.isEmpty()) { // если список юзеров пуст бросает 404
                throw new NotFoundException(NOT_FOUND_EX_MESSAGE);
            }
        } catch (PersistenceException e) {
            throw new DatabaseException(DATABASE_GET_EX_MESSAGE);
        }

        return userEntities;
    }

    /**
     * Метод для сохранения или обновления данных пользователя.
     */
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
        } catch (PersistenceException e) {
            throw new DatabaseException(DATABASE_SAVE_EX_MESSAGE);
        }
    }

    /**
     * Метод для поиска пользователя по username.
     * @return {@link UserEntity}
     */
    public UserEntity findByUsername(String username) {
        try (Session session = sessionFactory.getSession()) {
            return session.createQuery("FROM UserEntity WHERE username = :username", UserEntity.class)
                    .setParameter("username", username)
                    .uniqueResult();
        } catch (PersistenceException e) {
            throw new DatabaseException(DATABASE_GET_EX_MESSAGE);
        }
    }
}
