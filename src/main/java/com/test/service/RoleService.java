package com.test.service;

import com.test.db.dto.RoleDTO;
import com.test.db.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleDTO roleDTO;

    public List<Role> findAll(){
        return roleDTO.findAll();
    }

    public Role findById(long id){
        return roleDTO.findById(id);

    }

    public Role findByTitle(String title){
        return roleDTO.findByTitle(title);
    }
}
