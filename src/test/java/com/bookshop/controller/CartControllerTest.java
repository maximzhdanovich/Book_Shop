package com.bookshop.controller;

import com.bookshop.model.entity.Book;
import com.bookshop.model.entity.CustomUserDetail;
import com.bookshop.model.entity.User;
import com.bookshop.service.BookService;
import com.bookshop.service.CartService;
import com.bookshop.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartControllerTest {

    @Mock
    private CartService cartService;

    @Mock
    private BookService bookService;

    @Mock
    private UserService userService;

    @InjectMocks
    private BasketController basketController;

    @Test
    public void shouldCallBasketServiceAddSingleBookToBasketWhenAddBookToCart() {
        Book book = new Book();
        User user = new User();
        long id = 1L;
        book.setBookId(id);
        when(bookService.findById(id)).thenReturn(book);
        CustomUserDetail customUserDetail = new CustomUserDetail(user);
        basketController.addBookToCart(customUserDetail, book);
        verify(cartService).addSingleBookToBasket(customUserDetail, book);
    }

    @Test
    public void shouldCallBasketServiceDeleteBookFromBasketWhenUserDeleteBookFromCart() {
        User user = new User();
        CustomUserDetail customUserDetail = new CustomUserDetail(user);
        Book book = new Book();
        basketController.deleteBook(customUserDetail, book);
        verify(cartService).deleteBookFromBasket(customUserDetail, book);
    }

    @Test
    public void shouldCallBasketServiceSendBookToProcessingWhenUserSendBookToProcessing() {
        User user = new User();
        CustomUserDetail customUserDetail = new CustomUserDetail(user);
        Book book = new Book();
        basketController.bookToProcessing(customUserDetail, book);
        verify(cartService).sendBookToProcessing(customUserDetail, book);
    }

    @Test
    public void shouldCallBasketServiceSendAllBooksToProcessingWhenUserSendAllBooksToProcessing() {
        User user = new User();
        CustomUserDetail customUserDetail = new CustomUserDetail(user);
        basketController.allBookToProcessing(customUserDetail);
        verify(cartService).sendAllBooksToProcessing(customUserDetail);
    }

}
