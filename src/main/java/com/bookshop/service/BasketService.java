package com.bookshop.service;

import com.bookshop.model.dto.BasketDTO;
import com.bookshop.model.entity.Basket;
import com.bookshop.model.entity.Book;
import com.bookshop.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasketService {
    @Autowired
    private BasketDTO basketDTO;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    public List<Basket> findAll() {
        return basketDTO.findAll();
    }

    public Basket findById(long id) {
        return basketDTO.findById(id);
    }

    public void save(Basket basket) {
        basketDTO.save(basket);
    }

    public Basket getByUser(User user) {
        return userService.getCurrentUser(user).get().getBasket();
    }

    public Basket create(User user) {
        User currentUser = userService.getCurrentUser(user).get();
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
}
