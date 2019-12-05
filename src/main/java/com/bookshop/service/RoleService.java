package com.bookshop.service;

import com.bookshop.model.dto.RoleDTO;
import com.bookshop.model.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleDTO roleDTO;

    public List<Role> findAll() {
        return roleDTO.findAll();
    }

    public Role findById(long id) {
        return roleDTO.findById(id);
    }

    public Role findByTitle(String title) {
        return roleDTO.findByTitle(title);
    }
}
