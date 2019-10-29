package com.test.db.dto;

import com.test.db.dao.RoleDAO;
import com.test.db.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class RoleDTO  {
@Autowired
    private RoleDAO roleDAO;

    public List<Role> findAll(){
        return roleDAO.findAll();
    }

    public Role findById(long id){
        return roleDAO.findById(id);

    }
    public Role findByTitle(String title){
        return roleDAO.findByTitle(title);
    }
}
