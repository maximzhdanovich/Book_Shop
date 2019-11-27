package com.test.db.dto;

import com.test.db.dao.Book_ImageDao;
import com.test.db.model.Book;
import com.test.db.model.Book_Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class Book_ImageDTO {
    @Autowired
    private Book_ImageDao book_imageDAO;

    public void save(Book_Image book_image){
        book_imageDAO.save(book_image);
    }
    public List<Book_Image> findAll(){
        return book_imageDAO.findAll();
    }

    public Book_Image findById(long id){
        return book_imageDAO.findById(id);
    }

    public Book_Image findByAuthor(Book book){
        return book_imageDAO.findByBook(book);
    }

    public void deleteById(long id){
        book_imageDAO.deleteById(id);
    }

}
