//package com.test.db.dao;
//
//import com.test.db.model.Category;
//import org.hibernate.Hibernate;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import java.util.List;
//
//
//@EnableTransactionManagement
//@Component
//public class CategoryDAO implements IDAO<Category> {
//    @Autowired
//    private SessionFactory sessionFactory;
//
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
//    public void add(Category entity) {
//        sessionFactory.getCurrentSession().persist(entity);
//    }
//
//    @Override
//    public void update(Category entity) {
//        sessionFactory.getCurrentSession().update(entity);
//    }
//
//    @Override
//    public void delete(Category entity) {
//        sessionFactory.getCurrentSession().delete(entity);
//    }
//
//    @Override
//    public Category getOnId(Long id) {
//        Category category = sessionFactory.getCurrentSession().get(Category.class, id);
//        initGeneralInfo(category);
//        return category;
//    }
//
//    @Override
//    public List<Category> findAll() {
//        List<Category> categories = (List<Category>) sessionFactory.getCurrentSession().createCriteria(Category.class).list();
//        for (Category category : categories) {
//            initGeneralInfo(category);
//        }
//        return categories;
//    }
//
//    @Override
//    public void deleteOnId(Long id) {
//        sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().get(Category.class, id));
//    }
//
//    @Override
//    public void initGeneralInfo(Object object) {
//        // Hibernate.initialize(((Category)object).getBooks());
//    }
//}
