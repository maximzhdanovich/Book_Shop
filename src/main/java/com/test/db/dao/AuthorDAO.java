//package com.test.db.dao;
//
//import com.test.db.model.Author;
//import com.test.db.model.Author_Image;
//import org.hibernate.Hibernate;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.transaction.Transactional;
//import java.util.List;
//
//public class AuthorDAO implements IDAO<Author> {
//
//    @Autowired
//    private SessionFactory sessionFactory;
//
//    public SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }
//
//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Override
//    @Transactional
//    public void add(Author entity) {
//        sessionFactory.getCurrentSession().persist(entity);
//    }
//
//    @Override
//    @Transactional
//    public void update(Author entity) {
//        sessionFactory.getCurrentSession().update(entity);
//    }
//
//    @Override
//    @Transactional
//    public void delete(Author entity) {
//        sessionFactory.getCurrentSession().delete(entity);
//    }
//
//    @Override
//    @Transactional
//    public Author getOnId(Long id) {
//        Author author  = sessionFactory.getCurrentSession().get(Author.class, id);
//        initGeneralInfo(author);
//        return author;
//    }
//
//    @Override
//    @Transactional
//    public List<Author> findAll() {
//        List<Author> authors = (List<Author>) sessionFactory.getCurrentSession().createCriteria(Author.class).list();
//        for (Author author : authors) {
//            initGeneralInfo(author);
//        }
//        return authors;
//    }
//
//    @Override
//    @Transactional
//    public void deleteOnId(Long id) {
//        sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().get(Author.class, id));
//    }
//    @Override
//    public void initGeneralInfo(Object object) {
//        Hibernate.initialize(((Author) object).getName());
//        Hibernate.initialize(((Author) object).getSurname());
//    }
//}
