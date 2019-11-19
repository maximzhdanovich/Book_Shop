package com.test.service;

import com.test.db.dto.BasketDTO;
import com.test.db.model.Basket;
import com.test.db.model.Book;
import com.test.db.model.CustomUserDetail;
import com.test.db.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
        return userService.getCurrentUser(user).getBasket();
    }

    public Basket create(User user) {
        Basket basket = new Basket(user);
        save(basket);
        return basket;
    }

    public void addBooks(CustomUserDetail user, Map<String, String> form) {
        Basket basket = getByUser(user);
        for (String id : form.keySet()) {
            basket.getBooks().add(bookService.findById(Long.valueOf(id)));
        }
        save(basket);
    }

    public void addSingleBook(User user, Book book){
        Basket basket = getByUser(user);
        basket.getBooks().add(book);
        save(basket);
    }
}
