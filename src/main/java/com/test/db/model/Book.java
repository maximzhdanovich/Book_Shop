package com.test.db.model;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;

public class Book {
    private long id;
    private String category;
    private String author;
    private double price;
    private String titleRu;
    private String titleEn;

    public Book() {
    }

    public Book(double price, String titleRu, String titleEn, String category, String author) {
        this.price = price;
        this.titleRu = titleRu;
        this.titleEn = titleEn;
        this.category = category;
        this.author = author;
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NotNull
    @Size(max = 1024)
    @Column(name = "PRICE")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @NotNull
    @Size(max = 1024)
    @Column(name = "TITLE_RU")
    public String getTitleRu() {
        return titleRu;
    }

    public void setTitleRu(String titleRu) {
        this.titleRu = titleRu;
    }

    @NotNull
    @Size(max = 1024)
    @Column(name = "TITLE_EN")
    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    @NotNull
    @OneToMany(targetEntity = Category.class, mappedBy = "category")
    @JoinColumn(name = "FK_CATEGORY_ID")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @NotNull
    @OneToMany()
    @Column(name = "AUTHOR")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


}
