package com.bookshop.service;

import com.bookshop.model.dataService.BookDataService;
import com.bookshop.model.entity.Author;
import com.bookshop.model.entity.Book;
import com.bookshop.model.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Qualifier("bookService")
public class BookService {
    @Autowired
    private BookDataService bookDataService;

    @Autowired
    private BookImageService book_imageService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CategoryService categoryService;

    public void create(Book book, Author author) {
        book.setAuthor(author);
        save(book);
    }

    public List<Book> lastBook() {
        return bookDataService.lastBook();
    }

    public void create(Author author, Map<String, String> form, MultipartFile image) throws IOException {
//        Book book = new Book(price, titleRu, titleEn, description);
        Book book = new Book(Double.parseDouble(form.get("price")), form.get("titleRu"), form.get("titleEn"), form.get("description"));
        book.setAuthor(author);
        List<Category> category = new ArrayList<>();
        for (String s : form.keySet()) {
            if (form.get(s).equals("on")) {
                category.add(categoryService.findById(Long.valueOf(s)));
//                book.getCategories().add(categoryService.findById(Long.valueOf(s)));
            }
        }
        book.setCategories(category);
        save(book);
        book_imageService.add(image, book);
    }

    public void save(Book book) {
        bookDataService.save(book);
    }

    public Book findById(long id) {
        return bookDataService.findById(id);
    }

    public List<Book> findAll() {
        return bookDataService.findAll();
    }

    public List<Book> findByTitleEnOrTitleRu(String titleEn, String titleRu) {
        return bookDataService.findByTitleEnOrTitleRu(titleEn, titleRu);
    }

    public Page<Book> findAllPage(Pageable pageable) {
        return bookDataService.findAllPage(pageable);
    }

    public void deleteById(long id) {
        bookDataService.deleteById(id);
    }

    public Page<Book> findAllByAuthor(Author author, Pageable pageable) {
        return bookDataService.findAllByAuthor(author, pageable);
    }

    public Page<Book> findAllByCategories(Category category, Pageable pageable) {
        return bookDataService.findAllByCategories(category, pageable);
    }

    public void update(Book book, Map<String, String> form, MultipartFile image) throws IOException {
        book.setPrice(Double.parseDouble(form.get("price")));
        book.setTitleRu(form.get("titleRu"));
        book.setTitleEn(form.get("titleEn"));
        book.setAuthor(authorService.findBySurnameAndName(form.get("authorSurname"), form.get("authorName")).get());
        book.setDescription(form.get("description"));
        book.getCategories().clear();
        for (String s : form.keySet()) {
            if (form.get(s).equals("on")) {
                book.getCategories().add(categoryService.findById(Long.valueOf(s)));
            }
        }
        if (image != null && !image.getOriginalFilename().isEmpty()) {
            if (book.getImage() != null) {
                book_imageService.deleteById(book.getImage().getId());
            }
            book_imageService.add(image, book);
        }
        save(book);
    }
}
