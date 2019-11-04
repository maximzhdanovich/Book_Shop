package com.test.db.dto;

import com.test.db.dao.UserDAO;
import com.test.db.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDTO {
    @Autowired
    private UserDAO userDAO;

    public List<User> findAll(){
        return userDAO.findAll();
    }

    public User findById(long id){
        return userDAO.findById(id);
    }
    public void save(User user){
        userDAO.save(user);
    }
    public Optional<User> findByUsername(String name){
        return userDAO.findByUsername(name);
    }

    public User findByEmail(String email){
        return userDAO.findByEmail(email);
    }

    public void deleteById(long id){
        userDAO.deleteById(id);
    }

}

