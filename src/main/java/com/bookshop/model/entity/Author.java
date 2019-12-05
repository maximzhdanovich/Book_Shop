package com.bookshop.model.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "AUTHOR")
public class Author implements Serializable {
    private Long id;
    private String name;
    private String surname;
    private List<Book> books;
    private Author_Image image;

    public Author() {

    }


    @OneToOne
    @JoinColumn(name = "FK_IMAGE_ID")
    public Author_Image getImage() {
        return image;
    }

    public void setImage(Author_Image image) {
        this.image = image;
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @OneToMany(targetEntity = Book.class, mappedBy = "author", orphanRemoval = true)
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Column(name = "SURNAME")
    public String getSurname() {
        return surname;
    }


    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Author(String surname, String name) {
        this.name = name;
        this.surname = surname;
    }
}
