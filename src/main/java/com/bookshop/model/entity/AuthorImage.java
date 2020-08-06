package com.bookshop.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "AUTHOR_IMAGE")
public class AuthorImage {

    private Long authorId;
    private String authorImage;
//    private Author author;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long id) {
        this.authorId = id;
    }

    @NotNull
    @Column(name = "IMAGE")
    public String getAuthorImage() {
        return authorImage;
    }

    public void setAuthorImage(String authorImage) {
        this.authorImage = authorImage;
    }

//    @OneToOne
//    @JoinColumn(name = "FK_AUTHOR_ID")
//    public Author getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(Author author) {
//        this.author = author;
//    }

    public AuthorImage(Long authorId, String authorImage) {
        this.authorId = authorId;
        this.authorImage = authorImage;
//        this.author = author;
    }

    public AuthorImage(String authorImage) {
        this.authorImage = authorImage;
//        this.author = author;
    }

    public AuthorImage() {
    }

}
