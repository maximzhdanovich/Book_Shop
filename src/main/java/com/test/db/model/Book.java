package com.test.db.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
@Entity
@Table(name = "BOOK")
public class Book implements Serializable {
    private long id;
    private double price;
    private String titleRu;
    private String titleEn;
    private List<Category> categories;
    private Author author;

    @NotNull
    @ManyToOne(targetEntity = Author.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_AUTHOR_ID")
    @Access(AccessType.PROPERTY)
    public Author getAuthor() {
        return author;
    }
    @NotNull
    @ManyToMany
    @JoinTable(name = "BOOK_WITH_CATEGORY",
    joinColumns = @JoinColumn(name = "BOOK_ID"),
    inverseJoinColumns = @JoinColumn(name="CATEGORY_ID"))
    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Book(double price, String titleRu, String titleEn, Author author) {
        this.price = price;
        this.titleRu = titleRu;
        this.titleEn = titleEn;
        this.author = author;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @NotNull
    @Column(name = "PRICE")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @NotNull
    @Column(name = "TITLE_RU")
    public String getTitleRu() {
        return titleRu;
    }

    public void setTitleRu(String titleRu) {
        this.titleRu = titleRu;
    }

    @NotNull
    @Column(name = "TITLE_EN")
    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public Book() {
    }

}
