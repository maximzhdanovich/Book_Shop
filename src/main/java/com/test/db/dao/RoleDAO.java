//package com.test.db.dao;
//
//import com.test.db.model.Role;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.transaction.Transactional;
//import java.util.List;
//
//public class RoleDAO implements IDAO<Role> {
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
//    public void add(Role entity) {
//        sessionFactory.getCurrentSession().persist(entity);
//    }
//
//    @Override
//    @Transactional
//    public void update(Role entity) {
//        sessionFactory.getCurrentSession().update(entity);
//    }
//
//    @Override
//    @Transactional
//    public void delete(Role entity) {
//        sessionFactory.getCurrentSession().delete(entity);
//    }
//
//    @Override
//    @Transactional
//    public Role getOnId(Long id) {
//        Role role = sessionFactory.getCurrentSession().get(Role.class, id);
//        initGeneralInfo(role);
//        return role;
//    }
//
//    @Override
//    @Transactional
//    public List<Role> findAll() {
//        List<Role> roles = (List<Role>) sessionFactory.getCurrentSession().createCriteria(Role.class).list();
//        for (Role role : roles) {
//            initGeneralInfo(role);
//        }
//        return roles;
//    }
//
//    @Override
//    @Transactional
//    public void deleteOnId(Long id) {
//        sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().get(Role.class, id));
//    }
//
//    @Override
//    public void initGeneralInfo(Object object) {
////        Hibernate.initialize(((Role)object).getUsers());
//    }
//}
