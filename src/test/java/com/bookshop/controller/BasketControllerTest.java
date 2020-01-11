package com.bookshop.controller;

import com.bookshop.model.entity.Book;
import com.bookshop.model.entity.CustomUserDetail;
import com.bookshop.model.entity.User;
import com.bookshop.service.BasketService;
import com.bookshop.service.BookService;
import com.bookshop.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BasketControllerTest {

    @Mock
    private BasketService basketService;

    @Mock
    private BookService bookService;

    @Mock
    private UserService userService;

    @InjectMocks
    private BasketController basketController;

    @Test
    public void shouldCallBasketServiceAddSingleBookToBasketWhenAddBookToCart(){
        Book book = new Book();
        User user = new User();
        long id = 1L;
        book.setId(id);
        when(bookService.findById(id)).thenReturn(book);
        CustomUserDetail customUserDetail = new CustomUserDetail(user);
        basketController.addBookToCart(customUserDetail, book);
        verify(basketService).addSingleBookToBasket(customUserDetail,book);
    }

    @Test
    public void shouldCallBasketServiceDeleteBookFromBasketWhenUserDeleteBookFromCart(){
        User user = new User();
        CustomUserDetail customUserDetail = new CustomUserDetail(user);
        Book book = new Book();
        basketController.deleteBook(customUserDetail, book);
        verify(basketService).deleteBookFromBasket(customUserDetail,book);
    }

    @Test
    public void shouldCallBasketServiceSendBookToProcessingWhenUserSendBookToProcessing(){
        User user = new User();
        CustomUserDetail customUserDetail = new CustomUserDetail(user);
        Book book = new Book();
        basketController.bookToProcessing(customUserDetail,book);
        verify(basketService).sendBookToProcessing(customUserDetail,book);
    }

    @Test
    public void shouldCallBasketServiceSendAllBooksToProcessingWhenUserSendAllBooksToProcessing(){
        User user = new User();
        CustomUserDetail customUserDetail = new CustomUserDetail(user);
        basketController.AllBookToProcessing(customUserDetail);
        verify(basketService).sendAllBooksToProcessing(customUserDetail);
    }

}
