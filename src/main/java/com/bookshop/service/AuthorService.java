package com.bookshop.service;

import com.bookshop.model.dto.AuthorDTO;
import com.bookshop.model.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorDTO authorDTO;
    @Autowired
    private Author_ImageService authorImageService;

    public void save(Author author) {
        authorDTO.save(author);
    }

    public Optional<Author> findBySurnameAndName(String surname, String name) {
        if (!StringUtils.isEmpty(surname) && !StringUtils.isEmpty(name)) {
            return authorDTO.findBySurnameAndName(surname, name);
        } else {
            return Optional.empty();
        }
    }

    public List<Author> findBySurnameOrName(String surname, String name) {
        return authorDTO.findBySurnameOrName(surname, name);
    }

    public void deleteById(long id) {
        authorDTO.deleteById(id);
    }

    public Author findById(long id) {
        return authorDTO.findById(id);
    }

    public List<Author> findAll() {
        return authorDTO.findAll();
    }

    public void update(String surname, String name, Author author, MultipartFile image) throws IOException {
        author.setSurname(surname);
        author.setName(name);
        if (image != null && !image.getOriginalFilename().isEmpty()) {
            authorImageService.deleteById(author.getImage().getId());
            authorImageService.add(image, author);
        }
        save(author);
    }

    public void create(String surname, String name) {
        Author author = new Author(surname, name);
        save(author);
    }
}
