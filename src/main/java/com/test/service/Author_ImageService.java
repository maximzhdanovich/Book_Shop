package com.test.service;

import com.test.db.dto.AuthorDTO;
import com.test.db.dto.Author_ImageDTO;
import com.test.db.model.Author;
import com.test.db.model.Author_Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class Author_ImageService {
    @Autowired
    private Author_ImageDTO authorImageDTO;

    @Autowired
    private AuthorService authorService;

    @Value("${upload.path.author}")
    private String uploadPath;


    public void save(Author_Image author_image){
        authorImageDTO.save(author_image);
    }

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

    public void add(MultipartFile image,Author author) throws IOException {
        if (image != null && !image.getOriginalFilename().isEmpty()) {
            Author_Image author_image = new Author_Image();
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String fileName = uuidFile  + image.getOriginalFilename();
            image.transferTo(new File(uploadPath+"/"+fileName));
            author_image.setAuthorImage(fileName);
            author_image.setAuthor(author);
            save(author_image);
            author.setImage(author_image);
            authorService.save(author);
        }
    }

}
