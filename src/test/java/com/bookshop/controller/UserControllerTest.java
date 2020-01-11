package com.bookshop.controller;

import com.bookshop.model.entity.Basket;
import com.bookshop.model.entity.Book;
import com.bookshop.model.entity.User;
import com.bookshop.service.BasketService;
import com.bookshop.service.RoleService;
import com.bookshop.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.util.Collection;
import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private RoleService roleService;

    @Mock
    private BasketService basketService;

    @InjectMocks
    private UserController userController;

    @Test
    public void shouldCallUserServiceFindAllWhenGetUserList(){
        userController.userList(getModel());
        verify(userService).findAll();
    }

    @Test
    public void shouldCallRoleServiceFindAllWhenGetUserInformation(){
        User user = new User();
        userController.userEditInformation(user,getModel());
        verify(roleService).findAll();
    }

    @Test
    public void shouldCallUserServiceUpdateWhenSaveUserNewInformation(){
        User user = new User();
        String role = "role";
        String username = "username";
        String email = "email@email.com";
        String password = "password";
        userController.userSaveEditByAdmin(user, role, username, password, email);
        verify(userService).update(user, role, username, password, email);
    }

    @Test
    public void shouldReturnUserBasketWhenAdminGetUserBasket(){
        User user = new User();
        user.setBasket(new Basket());
        String result = userController.showUserBasket(user, getModel());
        assertThat(result).isEqualTo("adminUsersBasket");
    }

    @Test
    public void shouldCallBasketServiceApprovedSingleBookToUserWhenAdminApprovedBook(){
        Book book = new Book();
        User user = new User();
        long id = 1L;
        book.setId(id);
        userController.approvedSingleBookToUser(book, user);
        verify(basketService).approvedSingleBookToUser(book,user);
    }

    private Model getModel() {
        return new Model() {
            @Override
            public Model addAttribute(String s, Object o) {
                return null;
            }

            @Override
            public Model addAttribute(Object o) {
                return null;
            }

            @Override
            public Model addAllAttributes(Collection<?> collection) {
                return null;
            }

            @Override
            public Model addAllAttributes(Map<String, ?> map) {
                return null;
            }

            @Override
            public Model mergeAttributes(Map<String, ?> map) {
                return null;
            }

            @Override
            public boolean containsAttribute(String s) {
                return false;
            }

            @Override
            public Map<String, Object> asMap() {
                return null;
            }
        };
    }

}
