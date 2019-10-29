package com.test.db.dto;

import com.test.db.dao.AuthorDAO;
import com.test.db.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
@Component
public class AuthorDTO {
    @Autowired
    private AuthorDAO authorDAO;

    public List<Author> findAll(){
        return authorDAO.findAll();
    }

    public Author findById(long id){
        return authorDAO.findById(id);
    }

    public void deleteById(long id){
        authorDAO.deleteById(id);
    }

    public Author findBySurnameAndName(String surname, String name){
        return authorDAO.findBySurnameAndName(surname,name);
    }

    public List<Author> findBySurnameOrName(String surname, String name){
        return authorDAO.findBySurnameOrName(surname,name);
    }
    public void save(Author author){
        authorDAO.save(author);
    }
}
