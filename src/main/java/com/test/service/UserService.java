package com.test.service;

import com.test.db.dto.UserDTO;
import com.test.db.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDTO userDTO;

    public List<User> findAll(){
        return userDTO.findAll();
    }

    public User findById(long id){
        return userDTO.findById(id);
    }

    public User findByUsername(String name){
        return userDTO.findByUsername(name);
    }

    public User findByEmail(String email){
        return userDTO.findByEmail(email);
    }

    public void deleteById(long id){
        userDTO.deleteById(id);
    }

}
