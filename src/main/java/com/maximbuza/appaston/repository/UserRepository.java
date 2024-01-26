package com.maximbuza.appaston.repository;

import com.maximbuza.appaston.configuration.SessionFactoryConfig;
import com.maximbuza.appaston.dto.User;
import com.maximbuza.appaston.entity.UserEntity;
import com.maximbuza.appaston.exception.NotFoundException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class UserRepository {
    private final SessionFactoryConfig sessionFactoryConfig;

    public UserRepository(SessionFactoryConfig sessionFactoryConfig) {
        this.sessionFactoryConfig = sessionFactoryConfig;
    }

    public List<User> getAllUsersFromBd() { // получает список всех работников в бд. Работает
        try (Session session = sessionFactoryConfig.getSession()) {
            List<UserEntity> userEntities = session.createQuery("from UserEntity", UserEntity.class).getResultList();
            if (userEntities.isEmpty()) {
                throw new NotFoundException("No users in the repository");
            }
            Iterator<UserEntity> iterator = userEntities.iterator();
            List<User> userDtoList = new ArrayList<>();
            while (iterator.hasNext()) {
                UserEntity userEntity = iterator.next();
                User user = new User();
                user.setUsername(userEntity.getUsername());
                user.setPassword(userEntity.getPassword());
                userDtoList.add(user);
            }
            return userDtoList;
        } catch (HibernateException e) {
            throw new NotFoundException("не нашол"); // вот тут надо кидать свою ошибку
        }
    }

    public boolean isUserExist(String username) { // проверяет существование пользователя в бд по юзернейму. Работает
        try (Session session = sessionFactoryConfig.getSession()) {
            String hql = "SELECT COUNT(*) FROM UserEntity WHERE username = :username";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("username", username);
            Long count = query.uniqueResult();
            // Если количество больше 0, пользователь с таким именем существует
            return count > 0;
        }
    }
}
