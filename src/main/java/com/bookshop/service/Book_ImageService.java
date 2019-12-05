package com.bookshop.service;

import com.bookshop.model.dto.Book_ImageDTO;
import com.bookshop.model.entity.Book;
import com.bookshop.model.entity.Book_Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class Book_ImageService {
    @Autowired
    private Book_ImageDTO bookImageDTO;

    @Autowired
    private BookService bookService;

    @Value("${upload.path.book}")
    private String uploadPath;


    public void save(Book_Image author_image) {
        bookImageDTO.save(author_image);
    }

    public List<Book_Image> findAll() {
        return bookImageDTO.findAll();
    }

    public Book_Image findById(long id) {
        return bookImageDTO.findById(id);
    }

    public Book_Image findByAuthor(Book book) {
        return bookImageDTO.findByAuthor(book);
    }

    public void deleteById(long id) {
        bookImageDTO.deleteById(id);
    }

    public void add(MultipartFile image, Book book) throws IOException {
        if (image != null && !image.getOriginalFilename().isEmpty()) {
            Book_Image book_image = new Book_Image();
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String fileName = uuidFile + image.getOriginalFilename();
            image.transferTo(new File(uploadPath + "/" + fileName));
            book_image.setBookImage(fileName);
            book_image.setBook(book);
            save(book_image);
            book.setImage(book_image);
            bookService.save(book);
        }
    }
}
