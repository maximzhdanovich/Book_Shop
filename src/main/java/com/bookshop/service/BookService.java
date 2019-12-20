package com.bookshop.service;

import com.bookshop.model.dto.BookDTO;
import com.bookshop.model.entity.Author;
import com.bookshop.model.entity.Book;
import com.bookshop.model.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    private BookDTO bookDTO;

    @Autowired
    private Book_ImageService book_imageService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CategoryService categoryService;

    public void create(Book book, Author author) {
        book.setAuthor(author);
        save(book);
    }

    public void create(double price, String titleRu, String titleEn, String description, Author author,Map<String,String> form, MultipartFile image) throws IOException {
        Book book = new Book(price, titleRu, titleEn, description);
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
        bookDTO.save(book);
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

    public Page<Book> findAllPage(Pageable pageable) {
        return bookDTO.findAllPage(pageable);
    }

    public void deleteById(long id) {
        bookDTO.deleteById(id);
    }

    public Page<Book> findAllByAuthor(Author author, Pageable pageable) {
        return bookDTO.findAllByAuthor(author, pageable);
    }

    public Page<Book> findAllByCategories(Category category, Pageable pageable) {
        return bookDTO.findAllByCategories(category, pageable);
    }

    public void update(Book book, String titleEn, String titleRu, String authorSurname, String authorName, String description, Map<String, String> form, MultipartFile image) throws IOException {
        book.setTitleRu(titleRu);
        book.setTitleEn(titleEn);
        book.setAuthor(authorService.findBySurnameAndName(authorSurname, authorName).get());
        book.setDescription(description);
        book.getCategories().clear();
        for (String s : form.keySet()) {
            if (form.get(s).equals("on")) {
                book.getCategories().add(categoryService.findById(Long.valueOf(s)));
            }
        }
        if (image != null && !image.getOriginalFilename().isEmpty()) {
            if(book.getImage()!=null){
            book_imageService.deleteById(book.getImage().getId());}
            book_imageService.add(image, book);
        }
        save(book);
    }
}
