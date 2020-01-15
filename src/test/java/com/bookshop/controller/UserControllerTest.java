package com.bookshop.controller;

import com.bookshop.model.entity.Book;
import com.bookshop.model.entity.Cart;
import com.bookshop.model.entity.User;
import com.bookshop.service.CartService;
import com.bookshop.service.RoleService;
import com.bookshop.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import java.util.Collection;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private RoleService roleService;

    @Mock
    private CartService cartService;

    @InjectMocks
    private UserController userController;

    @Test
    public void shouldCallUserServiceFindAllWhenGetUserList() {
        userController.userList(getModel());
        verify(userService).findAll();
    }

    @Test
    public void shouldCallRoleServiceFindAllWhenGetUserInformation() {
        long id = 1L;
        userController.userEditInformation(id, getModel());
        verify(roleService).findAll();
    }

    @Test
    public void shouldCallUserServiceUpdateWhenSaveUserNewInformation() {
        User user = new User();
        String role = "role";
        String username = "username";
        String email = "email@email.com";
        String password = "password";
        userController.userSaveEditByAdmin(user, role, username, password, email);
        verify(userService).update(user, role, username, password, email);
    }

    @Test
    public void shouldReturnUserBasketWhenAdminGetUserBasket() {
        User user = new User();
        user.setCart(new Cart());
        long id = 1L;
        when(userService.findById(id)).thenReturn(user);
        String result = userController.showUserBasket(id, getModel());
        assertThat(result).isEqualTo("adminUsersBasket");
    }

    @Test
    public void shouldCallBasketServiceApprovedSingleBookToUserWhenAdminApprovedBook() {
        Book book = new Book();
        User user = new User();
        long id = 1L;
        book.setId(id);
        userController.approvedSingleBookToUser(book, user);
        verify(cartService).approvedSingleBookToUser(book, user);
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
