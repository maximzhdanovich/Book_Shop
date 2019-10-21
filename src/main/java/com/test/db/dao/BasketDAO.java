//package com.test.db.dao;
//
////import com.test.db.model.Author;
////import com.test.db.model.Author_Image;
////import com.test.db.model.Basket;
//import org.hibernate.Hibernate;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.transaction.Transactional;
//import java.util.List;
//
//public class BasketDAO implements IDAO<Basket>{
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
//    public void add(Basket entity) {
//        sessionFactory.getCurrentSession().persist(entity);
//    }
//
//    @Override
//    @Transactional
//    public void update(Basket entity) {
//        sessionFactory.getCurrentSession().update(entity);
//    }
//
//    @Override
//    @Transactional
//    public void delete(Basket entity) {
//        sessionFactory.getCurrentSession().delete(entity);
//    }
//
//    @Override
//    @Transactional
//    public Basket getOnId(Long id) {
//        Basket basket  = sessionFactory.getCurrentSession().get(Basket.class, id);
//        initGeneralInfo(basket);
//        return basket;
//    }
//
//    @Override
//    @Transactional
//    public List<Basket> findAll() {
//        List<Basket> baskets = (List<Basket>) sessionFactory.getCurrentSession().createCriteria(Basket.class).list();
//        for (Basket basket : baskets) {
//            initGeneralInfo(basket);
//        }
//        return baskets;
//    }
//
//    @Override
//    @Transactional
//    public void deleteOnId(Long id) {
//        sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().get(Basket.class, id));
//    }
//    @Override
//    public void initGeneralInfo(Object object) {
//        Hibernate.initialize(((Basket) object).getUser());
//    }
//}
