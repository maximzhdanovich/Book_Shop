package com.test.db.dto;

import com.test.db.dao.BookDAO;
import com.test.db.model.Author;
import com.test.db.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Qualifier("bookDAO")
public class BookDTO {
    @Autowired

    private BookDAO bookDAO;

    public void save(Book book) {
        bookDAO.save(book);
    }

    public Book findById(long id) {
        return bookDAO.findById(id);
    }

    public List<Book> findAll() {
        return bookDAO.findAll();
    }


    public List<Book> findByTitleEnOrTitleRu(String titleEn, String titleRu) {
        return bookDAO.findByTitleEnOrTitleRu(titleEn, titleRu);
    }
}
