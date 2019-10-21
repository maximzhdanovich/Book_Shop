package com.test.db.dto;

import com.test.db.dao.IDAO;
import com.test.db.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class RoleDTO implements IDTO<Role> {
    @Autowired
    @Qualifier("roleDAO")
    private IDAO<Role> roleDAO;

    @Override
    public void add(Role entity) {
        roleDAO.add(entity);
    }

    @Override
    public void update(Role entity) {
        roleDAO.update(entity);
    }

    @Override
    public void delete(Role entity) {
        roleDAO.delete(entity);
    }

    @Override
    public Role getOnId(Long id) {
        return roleDAO.getOnId(id);
    }

    @Override
    public List<Role> findAll() {
        return roleDAO.findAll();
    }

    @Override
    public void deleteOnId(Long id) {
        roleDAO.deleteOnId(id);
    }
}
