package com.bookshop.service;

import com.bookshop.model.dataService.CartDataService;
import com.bookshop.model.entity.Cart;
import com.bookshop.model.entity.Book;
import com.bookshop.model.entity.CustomUserDetail;
import com.bookshop.model.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {

    @Mock
    private CartDataService cartDataService;

    @Mock
    private UserService userService;

    @Mock
    private BookService bookService;

    @InjectMocks
    private CartService cartService;

    @Test
    public void shouldReturnEmptyListWhenCallFindAllAndDataBaseIsEmpty(){
        when(cartDataService.findAll()).thenReturn(Collections.emptyList());
        assertThat(cartService.findAll()).isEmpty();
    }

    @Test
    public void shouldReturnBasketWhenCallFindByIdAndDataBaseIsNotEmpty(){
        long id = 1L;
        Cart cart = new Cart();
        when(cartDataService.findById(id)).thenReturn(cart);
        assertThat(cartService.findById(id)).isEqualTo(cart);
    }

    @Test
    public void shouldCallBasketDataServiceSaveWhenCallBasketServiceSave(){
        cartService.save(new Cart());
        verify(cartDataService).save(isA(Cart.class));
    }

    @Test
    public void shouldReturnBasketWhenCallBasketServiceCreate(){
        Cart cart = cartService.create(getCustomUserDetail());
        assertThat(cart).isInstanceOf(Cart.class);
    }

    @Test
    public void shouldCallBasketDataServiceSaveWhenCallAddSingleBookToBasket(){
        Book book = getBook();
        cartService.addSingleBookToBasket(getCustomUserDetail(),book);
        verify(cartDataService).save(isA(Cart.class));
    }

    @Test
    public void shouldCallBasketDataServiceSaveWhenCallDeleteBookFromBasket(){
        Book book = getBook();
        cartService.deleteBookFromBasket(getCustomUserDetail(),book);
        verify(cartDataService).save(isA(Cart.class));
    }

    @Test
    public void shouldCallBasketDataServiceSaveWhenCallSendBookToProcessing(){
        Book book = getBook();
        cartService.sendBookToProcessing(getCustomUserDetail(),book);
        verify(cartDataService).save(isA(Cart.class));
    }

    @Test
    public void shouldCallBasketDataServiceSaveWhenCallApprovedSingleBookToUser(){
        Book book = getBook();
        cartService.approvedSingleBookToUser(book, getCustomUserDetail());
        verify(cartDataService).save(isA(Cart.class));
    }

    private Book getBook() {
        Book book = new Book();
        book.setId(1L);
        return book;
    }

    @Test
    public void shouldCallBasketDataServiceSaveWhenCallSendAllBooksToProcessing(){
        cartService.sendAllBooksToProcessing(getCustomUserDetail());
        verify(cartDataService).save(isA(Cart.class));
    }

    private CustomUserDetail getCustomUserDetail() {
        User user = new User();
        Cart cart = new Cart(1L,new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),user);
        user.setCart(cart);
        CustomUserDetail customUserDetail = new CustomUserDetail(user);
        when(userService.getCurrentUser(customUserDetail)).thenReturn(user);
        return customUserDetail;
    }
}
