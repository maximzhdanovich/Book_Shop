package com.test.db.dao;

import com.test.db.model.Role;
import com.test.db.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleDAO extends CrudRepository<Role, Long> {
    List<Role> findAll();

    Role findById(long id);

    Role findByTitle(String title);
}
