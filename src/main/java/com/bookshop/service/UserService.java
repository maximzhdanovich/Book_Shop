package com.bookshop.service;

import com.bookshop.model.dto.UserDTO;
import com.bookshop.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserDTO userDTO;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BasketService basketService;

    public List<User> findAll() {
        return userDTO.findAll();
    }

    public Optional<User> findById(long id) {
        return userDTO.findById(id);
    }

    public Optional<User> findByUsername(String name) {
        return userDTO.findByUsername(name);
    }

    public Optional<User> findByEmail(String email) {
        return userDTO.findByEmail(email);
    }

    public void deleteById(long id) {
        userDTO.deleteById(id);
    }


    public void save(User user) {
        userDTO.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

    public Optional<User> getCurrentUser(User user) {
        if (findById(user.getId()).isPresent())
            return findById(user.getId());
        return Optional.empty();
    }

    public void create(User user) {
        user.setActive(true);
        user.setRole(roleService.findByTitle("USER"));
        save(user);
        basketService.create(user);
    }

    public void update(User user, String role, String username, String password, String email) {
        user.setUsername(username);
        user.setRole(roleService.findByTitle(role));
        user.setEmail(email);
        user.setPassword(password);
        save(user);
    }

    public void update(User user, String username, String password, String email) {
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        save(user);
    }
}
