package com.bookshop.service;

import com.bookshop.model.dataService.BasketDataService;
import com.bookshop.model.entity.Basket;
import com.bookshop.model.entity.Book;
import com.bookshop.model.entity.CustomUserDetail;
import com.bookshop.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasketService {
    @Autowired
    private BasketDataService basketDataService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    public List<Basket> findAll() {
        return basketDataService.findAll();
    }

    public Basket findById(long id) {
        return basketDataService.findById(id);
    }

    public void save(Basket basket) {
        basketDataService.save(basket);
    }

    public Basket getByUser(User user) {
        return userService.getCurrentUser(user).getBasket();
    }

    public Basket create(User user) {
        User currentUser = userService.getCurrentUser(user);
        Basket basket = new Basket(currentUser);
        save(basket);
        currentUser.setBasket(basket);
        userService.save(currentUser);
        return basket;
    }


    public void addSingleBook(User user, Book book) {
        Basket basket = getByUser(user);
        if (basket == null) {
            basket = create(user);
        }
        List<Book> books = basket.getBooks();
        if (basket.getBooks() == null) {
            books = new ArrayList<>();
        }
        books.add(book);
        save(basket);
    }

    public void deleteBookFromBasket(CustomUserDetail user, Book book){
        Basket basket = userService.getCurrentUser(user).getBasket();
        basket.getBooks().remove(bookService.findById(book.getId()));
        save(basket);
    }

    public void sendBookToProcessing(CustomUserDetail user, Book book){
        Basket basket = userService.getCurrentUser(user).getBasket();
        basket.getBooks().remove(bookService.findById(book.getId()));
        basket.getBooksInProcessing().add(bookService.findById(book.getId()));
        save(basket);
    }

    public void sendAllBookToProcessing(CustomUserDetail user){
        Basket basket = userService.getCurrentUser(user).getBasket();
        basket.getBooksInProcessing().addAll(basket.getBooks());
        basket.getBooks().clear();
        save(basket);
    }
}
