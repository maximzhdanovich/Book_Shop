package com.test.db.dao;

import com.test.db.model.Author_Image;
import com.test.db.model.Book;
import com.test.db.model.Category;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

public class Author_ImageDAO implements IDAO<Author_Image> {

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
    public void add(Author_Image entity) {
        sessionFactory.getCurrentSession().persist(entity);
    }

    @Override
    @Transactional
    public void update(Author_Image entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    @Override
    @Transactional
    public void delete(Author_Image entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    @Transactional
    public Author_Image getOnId(Long id) {
        Author_Image image = sessionFactory.getCurrentSession().get(Author_Image.class, id);
        initGeneralInfo(image);
        return image;
    }

    @Override
    @Transactional
    public List<Author_Image> findAll() {
        List<Author_Image> images = (List<Author_Image>) sessionFactory.getCurrentSession().createCriteria(Author_Image.class).list();
        for (Author_Image image : images) {
            initGeneralInfo(image);
        }
        return images;
    }

    @Override
    @Transactional
    public void deleteOnId(Long id) {
        sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().get(Author_Image.class, id));
    }
    @Override
    public void initGeneralInfo(Object object) {

            Hibernate.initialize(((Author_Image) object).getAuthor());
    }
}
