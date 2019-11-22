package com.test.service;

import com.test.db.dto.UserDTO;
import com.test.db.model.Basket;
import com.test.db.model.CustomUserDetail;
import com.test.db.model.User;
import com.test.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
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

    public User findById(long id) {
        return userDTO.findById(id);
    }

    public Optional<User> findByUsername(String name) {
        return userDTO.findByUsername(name);
    }

    public User findByEmail(String email) {
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

    public User getCurrentUser(User user) {
        if (user == null) {
            throw new UserNotFoundException();
        }
        return findById(user.getId());
    }

    public void create(User user) {
        user.setActive(true);
        user.setRole(roleService.findByTitle("USER"));
        save(user);
        user.setBasket(basketService.create(user));
        save(user);
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
