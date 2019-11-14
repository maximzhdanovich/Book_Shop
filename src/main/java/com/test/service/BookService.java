package com.test.service;

import com.test.db.dto.BookDTO;
import com.test.db.model.Author;
import com.test.db.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("bookService")
public class BookService {
    @Autowired
    private BookDTO bookDTO;

    public void create(double price, String titleRu, String titleEn, Author author) {
        Book book = new Book(price, titleRu, titleEn);
        book.setAuthor(author);
        save(book);
    }

    public void save(Book book) {
        bookDTO.save(book);
    }

    public BookDTO getBookDTO() {
        return bookDTO;
    }

    public void setBookDTO(BookDTO bookDTO) {
        this.bookDTO = bookDTO;
    }

    public Book findById(long id) {
        return bookDTO.findById(id);
    }

    public List<Book> findAll() {
        return bookDTO.findAll();
    }

    public List<Book> findByTitleEnOrTitleRu(String titleEn, String titleRu) {
        return bookDTO.findByTitleEnOrTitleRu(titleEn, titleRu);
    }

    public void deleteById(long id) {
        bookDTO.deleteById(id);
    }
}
