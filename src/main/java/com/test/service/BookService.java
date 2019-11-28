package com.test.service;

import com.test.db.dto.BookDTO;
import com.test.db.model.Author;
import com.test.db.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Qualifier("bookService")
public class BookService {
    @Autowired
    private BookDTO bookDTO;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CategoryService categoryService;

    public void create(Book book,Author author){
        book.setAuthor(author);
        save(book);
    }

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

    public void update(Book book,  String titleEn, String titleRu, String authorSurname, String authorName,String description, Map<String, String> form){
        book.setTitleRu(titleRu);
        book.setTitleEn(titleEn);
        book.setAuthor(authorService.findBySurnameAndName(authorSurname, authorName));
        book.setDescription(description);
        book.getCategories().clear();
        for (String s : form.keySet()) {
            if (form.get(s).equals("on")) {
                book.getCategories().add(categoryService.findById(Integer.valueOf(s)));
            }
        }
        save(book);
    }
}
