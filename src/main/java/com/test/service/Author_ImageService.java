package com.test.service;

import com.test.db.dto.Author_ImageDTO;
import com.test.db.model.Author;
import com.test.db.model.Author_Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Author_ImageService {
    @Autowired
    private Author_ImageDTO authorImageDTO;

    public List<Author_Image> findAll(){
        return authorImageDTO.findAll();
    }

    public Author_Image findById(long id){
        return authorImageDTO.findById(id);
    }

    public Author_Image findByAuthor(Author author){
        return authorImageDTO.findByAuthor(author);
    }

    public void deleteById(long id){
        authorImageDTO.deleteById(id);
    }

}
