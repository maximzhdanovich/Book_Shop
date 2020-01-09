package com.bookshop.service;

import com.bookshop.model.dataService.BasketDataService;
import com.bookshop.model.entity.Basket;
import com.bookshop.model.entity.Book;
import com.bookshop.model.entity.CustomUserDetail;
import com.bookshop.model.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.management.BadAttributeValueExpException;
import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BasketServiceTest {

    @Mock
    private BasketDataService basketDataService;

    @Mock
    private UserService userService;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BasketService basketService;

    @Test
    public void shouldReturnEmptyListWhenCallFindAllAndDataBaseIsEmpty(){
        when(basketDataService.findAll()).thenReturn(Collections.emptyList());
        assertThat(basketService.findAll()).isEmpty();
    }

    @Test
    public void shouldReturnBasketWhenCallFindByIdAndDataBaseIsNotEmpty(){
        long id = 1L;
        Basket basket = new Basket();
        when(basketDataService.findById(id)).thenReturn(basket);
        assertThat(basketService.findById(id)).isEqualTo(basket);
    }

    @Test
    public void shouldCallBasketDataServiceSaveWhenCallBasketServiceSave(){
        basketService.save(new Basket());
        verify(basketDataService).save(isA(Basket.class));
    }

    @Test
    public void shouldReturnBasketWhenCallBasketServiceCreate(){
        Basket basket = basketService.create(getCustomUserDetail());
        assertThat(basket).isInstanceOf(Basket.class);
    }

    @Test
    public void shouldCallBasketDataServiceSaveWhenCallAddSingleBookToBasket(){
        Book book = getBook();
        basketService.addSingleBookToBasket(getCustomUserDetail(),book);
        verify(basketDataService).save(isA(Basket.class));
    }

    @Test
    public void shouldCallBasketDataServiceSaveWhenCallDeleteBookFromBasket(){
        Book book = getBook();
        basketService.deleteBookFromBasket(getCustomUserDetail(),book);
        verify(basketDataService).save(isA(Basket.class));
    }

    @Test
    public void shouldCallBasketDataServiceSaveWhenCallSendBookToProcessing(){
        Book book = getBook();
        basketService.sendBookToProcessing(getCustomUserDetail(),book);
        verify(basketDataService).save(isA(Basket.class));
    }

    @Test
    public void shouldCallBasketDataServiceSaveWhenCallApprovedSingleBookToUser(){
        Book book = getBook();
        basketService.approvedSingleBookToUser(book, getCustomUserDetail());
        verify(basketDataService).save(isA(Basket.class));
    }

    private Book getBook() {
        Book book = new Book();
        book.setId(1L);
        return book;
    }

    @Test
    public void shouldCallBasketDataServiceSaveWhenCallSendAllBooksToProcessing(){
        basketService.sendAllBooksToProcessing(getCustomUserDetail());
        verify(basketDataService).save(isA(Basket.class));
    }

    private CustomUserDetail getCustomUserDetail() {
        User user = new User();
        Basket basket = new Basket(1L,new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),user);
        user.setBasket(basket);
        CustomUserDetail customUserDetail = new CustomUserDetail(user);
        when(userService.getCurrentUser(customUserDetail)).thenReturn(user);
        return customUserDetail;
    }
}
