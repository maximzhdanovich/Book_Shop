package com.bookshop.service;

import com.bookshop.model.dao.UserDAO;
import com.bookshop.model.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserDAO userDAO;

    @Test
    public void addUser() {
        User user = new User("1", "1@1", "1");
//        basketService.create(user);
        boolean isUserCreated = userService.create(user);
        Assert.assertTrue(isUserCreated);
        Assert.assertNotNull(user.getRole());
    }
}