package com.bookshop.model.dto;

import com.bookshop.model.dao.Author_ImageDAO;
import com.bookshop.model.entity.Author;
import com.bookshop.model.entity.Author_Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Author_ImageDTO {
    @Autowired
    private Author_ImageDAO author_imageDAO;

    public void save(Author_Image author_image) {
        author_imageDAO.save(author_image);
    }

    public List<Author_Image> findAll() {
        return author_imageDAO.findAll();
    }

    public Author_Image findById(long id) {
        return author_imageDAO.findById(id);
    }

    public Author_Image findByAuthor(Author author) {
        return author_imageDAO.findByAuthor(author);
    }

    public void deleteById(long id) {
        author_imageDAO.deleteById(id);
    }
}
