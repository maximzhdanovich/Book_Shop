package com.bookshop.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "BOOK_IMAGE")
public class BookImage {
    private Long id;
    private String bookImage;
    private Book book;

    @NotNull
    @Column(name = "IMAGE")
    public String getBookImage() {
        return bookImage;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }

    @NotNull
    @OneToOne
    @JoinColumn(name = "FK_BOOK_ID")
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookImage(Long id, String bookImage, Book book) {
        this.id = id;
        this.bookImage = bookImage;
        this.book = book;
    }

    public BookImage(String bookImage, Book book) {
        this.bookImage = bookImage;
        this.book = book;
    }

    public BookImage() {
    }

}
