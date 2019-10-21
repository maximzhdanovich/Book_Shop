package com.test.db.dao;

import com.test.db.model.Book;
//import com.test.db.model.Category;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.util.List;

@EnableTransactionManagement
@Component
public class BookDAO implements IDAO<Book> {

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
    public void add(Book entity) {
        sessionFactory.getCurrentSession().persist(entity);
    }

    @Override
    @Transactional
    public void update(Book entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    @Override
    @Transactional
    public void delete(Book entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    @Transactional
    public Book getOnId(Long id) {
        Book book = sessionFactory.getCurrentSession().get(Book.class, id);
        initGeneralInfo(book);
        return book;
    }

    @Override
    @Transactional
    public List<Book> findAll() {
        List<Book> books = (List<Book>) sessionFactory.getCurrentSession().createCriteria(Book.class).list();
        for (Book book : books) {
            initGeneralInfo(book);
        }
        return books;
    }

    @Override
    @Transactional
    public void deleteOnId(Long id) {
        sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().get(Book.class, id));
    }

    @Override
    public void initGeneralInfo(Object object) {
//        Hibernate.initialize(((Book) object).getCategory());
//        Hibernate.initialize(((Book) object).getAuthor());
//        Hibernate.initialize(((Book) object).getPrice());
    }

}
