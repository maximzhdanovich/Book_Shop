package com.test.service;

import com.test.db.dao.AuthorDAO;
import com.test.db.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthorService  {
    @Autowired
    private AuthorDAO authorDAO;

    public void save(Author author) {
        authorDAO.save(author);
    }

    public Author findBySurnameAndName(String surname, String name) {
        if (surname != null && !surname.equals("") && name != null && !name.equals("")) {
            return authorDAO.findBySurnameAndName(surname, name);
        } else {
            return null;
        }
    }
    public void deleteById(long id){
        authorDAO.deleteById(id);
    }

    public Author findById(long id){
        return authorDAO.findById(id);
    }

    public Set<Author> findAll(){
     return authorDAO.findAll();
    }
}
