package com.test.db.model;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

public class Book implements Serializable {
    private long id;
    private Set<Category> category;
    private Author author;
    private double price;
    private String titleRu;
    private String titleEn;
    private Basket basket;

    public Book() {
    }

    @ManyToMany
    @JoinTable(name = "BOOK_WITH_BASKET",
            joinColumns = @JoinColumn(name = "BOOK_ID"),
            inverseJoinColumns = @JoinColumn(name="BASKET_ID"))
    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public Book(double price, String titleRu, String titleEn) {
        this.price = price;
        this.titleRu = titleRu;
        this.titleEn = titleEn;
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
    @ManyToMany
    @JoinTable(name = "BOOK_WITH_CATEGORY",
    joinColumns = @JoinColumn(name = "BOOK_ID"),
    inverseJoinColumns = @JoinColumn(name="CATEGORY_ID"))
    public Set<Category> getCategory() {
        return category;
    }

    public void setCategory(Set<Category> category) {
        this.category = category;
    }

    @NotNull
    @ManyToOne(targetEntity = Author.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_AUTHOR_ID")
    @Access(AccessType.PROPERTY)
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
