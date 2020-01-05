package com.bookshop.model.dataService;

import com.bookshop.model.dao.Author_ImageDAO;
import com.bookshop.model.entity.AuthorImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorImageDataService {
    @Autowired
    private Author_ImageDAO author_imageDAO;

    public void save(AuthorImage author_image) {
        author_imageDAO.save(author_image);
    }

    public List<AuthorImage> findAll() {
        return author_imageDAO.findAll();
    }

    public AuthorImage findById(long id) {
        return author_imageDAO.findById(id);
    }


    public void deleteById(long id) {
        author_imageDAO.deleteById(id);
    }
}
