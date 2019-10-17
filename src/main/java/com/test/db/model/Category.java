package com.test.db.model;

import com.sun.istack.internal.NotNull;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "CATEGORY")
public class Category {

    protected long id;
    private String titleRu;
    private String titleEn;
    private Set<Book> books;

    @ManyToOne(targetEntity = Book.class)
    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }


    public Category() {
    }

    public Category(String titleRu, String titleEn) {
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
    @Size(max = 128)
    @Column(name = "TITLE_RU", nullable = false, length = 128)
    public String getTitleRu() {
        return titleRu;
    }

    public void setTitleRu(String titleRu) {
        this.titleRu = titleRu;
    }

    public String getTitleEn() {
        return titleEn;
    }

    @NotNull
    @Size(max = 128)
    @Column(name = "TITLE_EN", nullable = false, length = 128)
    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }
}
