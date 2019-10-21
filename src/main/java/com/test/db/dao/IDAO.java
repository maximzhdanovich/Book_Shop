package com.test.db.dao;

import java.util.List;

public interface IDAO  <T> {
    public void add(T entity);

    public void update(T entity);

    public void delete(T entity);

    public T getOnId(Long id);

    public List<T> findAll();

    public void deleteOnId(Long id);

    public void initGeneralInfo(Object object);

}
