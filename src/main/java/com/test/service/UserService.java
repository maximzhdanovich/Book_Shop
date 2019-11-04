package com.test.service;

import com.test.db.dto.UserDTO;
import com.test.db.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService{
    @Autowired
    private UserDTO userDTO;

    public List<User> findAll(){
        return userDTO.findAll();
    }

    public User findById(long id){
        return userDTO.findById(id);
    }

    public Optional<User> findByUsername(String name){
        return userDTO.findByUsername(name);
    }

    public User findByEmail(String email){
        return userDTO.findByEmail(email);
    }

    public void deleteById(long id){
        userDTO.deleteById(id);
    }


    public void save(User user) {
        userDTO.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
