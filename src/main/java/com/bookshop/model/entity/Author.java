package com.bookshop.model.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "AUTHOR")
public class Author {

    private Long authorId;
    private String name;
    private String surname;
    private List<Book> books;
    private AuthorImage image;

    @OneToOne
    @JoinColumn(name = "FK_IMAGE_ID")
    public AuthorImage getImage() {
        return image;
    }

    public void setImage(AuthorImage image) {
        this.image = image;
    }

    @Id
    @Column(name = "author_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getAuthorId() {
        return authorId;
    }

    @OneToMany(targetEntity = Book.class, mappedBy = "author", orphanRemoval = true)
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void setAuthorId(Long id) {
        this.authorId = id;
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

    public Author(Long authorId, String name, String surname, List<Book> books, AuthorImage image) {
        this.authorId = authorId;
        this.name = name;
        this.surname = surname;
        this.books = books;
        this.image = image;
    }

    public Author(String surname, String name) {
        this.name = name;
        this.surname = surname;
    }

    public Author() {

    }

}
