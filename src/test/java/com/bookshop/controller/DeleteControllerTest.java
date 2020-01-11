package com.bookshop.controller;
import com.bookshop.model.entity.User;
import com.bookshop.service.BookService;
import com.bookshop.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeleteControllerTest {

    @Mock
    private BookService bookService;

    @Mock
    private UserService userService;

    @InjectMocks
    private DeleteController deleteController;

    @Test
    public void shouldCallBookServiceDeleteByIdWhenDeleteBook(){
        long id = 1L;
        deleteController.deleteBook(id);
        verify(bookService).deleteById(id);
    }

    @Test
    public void shouldCallUserServiceDeleteByIdWhenUserDeleteAccount(){
        User user = new User();
        long id = 1L;
        user.setId(id);
        when(userService.getCurrentUser(user)).thenReturn(user);
        deleteController.deleteAccount(user);
        verify(userService).deleteById(id);
    }
}
