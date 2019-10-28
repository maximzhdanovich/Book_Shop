package com.test.service;

import com.test.db.model.Author;
import com.test.db.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class BookService {
    @Autowired
    private BookService bookDAO;

    public void save(Book book) {
        bookDAO.save(book);
    }

    public Book findById(long id) {
        return bookDAO.findById(id);
    }

    public List<Book> findAll() {
        return bookDAO.findAll();
    }

    public Set<Book> findByAuthor(Author author) {
        return bookDAO.findByAuthor(author);
    }

    public List<Book> findByTitleEnOrTitleRu(String titleEn, String titleRu) {
        return bookDAO.findByTitleEnOrTitleRu(titleEn, titleRu);
    }
}
