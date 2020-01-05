package com.bookshop.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "AUTHOR_IMAGE")
public class AuthorImage implements Serializable {
    private Long id;
    private String authorImage;
    private Author author;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Column(name = "IMAGE")
    public String getAuthorImage() {
        return authorImage;
    }

    public void setAuthorImage(String authorImage) {
        this.authorImage = authorImage;
    }

    public AuthorImage(String authorImage, Author author) {
        this.authorImage = authorImage;
        this.author = author;
    }

    @NotNull
    @OneToOne
    @JoinColumn(name = "FK_AUTHOR_ID")
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public AuthorImage() {
    }

}
