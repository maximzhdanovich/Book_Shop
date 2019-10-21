package com.test.db.dto;

import java.io.Serializable;
import java.util.List;

public interface IDTO <T extends Serializable> {

    public void add(T entity);

    public void update(T entity);

    public void delete(T entity);

    public T getOnId(Long id);

    public List<T> findAll();

    public void deleteOnId(Long id);
}