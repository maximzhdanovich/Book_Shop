package com.test.db.dao;

import com.test.db.model.User;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

public class UserDAO implements IDAO<User> {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void add(User entity) {
        sessionFactory.getCurrentSession().persist(entity);
    }

    @Override
    @Transactional
    public void update(User entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    @Override
    @Transactional
    public void delete(User entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    @Transactional
    public User getOnId(Long id) {
        User user  = sessionFactory.getCurrentSession().get(User.class, id);
        initGeneralInfo(user);
        return user;
    }

    @Override
    @Transactional
    public List<User> findAll() {
        List<User> users = (List<User>) sessionFactory.getCurrentSession().createCriteria(User.class).list();
        for (User user : users) {
            initGeneralInfo(user);
        }
        return users;
    }

    @Override
    @Transactional
    public void deleteOnId(Long id) {
        sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().get(User.class, id));
    }
    @Override
    public void initGeneralInfo(Object object) {
        Hibernate.initialize(((User) object).getBasket());
        Hibernate.initialize(((User) object).getRole().getUsers());
    }

    @SuppressWarnings("unchecked")
    public User findByUserName(String username) {

        List<User> users = null;

        users = sessionFactory.getCurrentSession()
                .createQuery("from User where NAME=?")
                .setParameter(0, username)
                .list();

        for (User user : users) {
            initGeneralInfo(user);
        }

        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }
}
