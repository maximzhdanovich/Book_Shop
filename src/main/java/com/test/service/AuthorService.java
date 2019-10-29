package com.test.service;

import com.test.db.dto.AuthorDTO;
import com.test.db.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AuthorService  {
    @Autowired
    private AuthorDTO authorDTO;

    public void save(Author author) {
        authorDTO.save(author);
    }

    public Author findBySurnameAndName(String surname, String name) {
        if (surname != null && !surname.equals("") && name != null && !name.equals("")) {
            return authorDTO.findBySurnameAndName(surname, name);
        } else {
            return null;
        }
    }
    public void deleteById(long id){
        authorDTO.deleteById(id);
    }

    public Author findById(long id){
        return authorDTO.findById(id);
    }

    public List<Author> findAll(){
     return authorDTO.findAll();
    }
}
