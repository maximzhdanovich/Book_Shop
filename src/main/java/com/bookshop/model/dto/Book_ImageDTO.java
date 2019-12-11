package com.bookshop.model.dto;

import com.bookshop.model.dao.Book_ImageDao;
import com.bookshop.model.entity.Book_Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Book_ImageDTO {
    @Autowired
    private Book_ImageDao book_imageDAO;

    public void save(Book_Image book_image) {
        book_imageDAO.save(book_image);
    }

    public List<Book_Image> findAll() {
        return book_imageDAO.findAll();
    }

    public Book_Image findById(long id) {
        return book_imageDAO.findById(id);
    }

    public void deleteById(long id) {
        book_imageDAO.deleteById(id);
    }

}
