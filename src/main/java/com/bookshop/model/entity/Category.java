package com.bookshop.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "CATEGORY")
public class Category implements Serializable {

    private long categoryId;
    private String categoryTitleRu;
    private String categoryTitleEn;
    private List<Book> Books;

    @ManyToMany
    @JoinTable(name = "BOOK_WITH_CATEGORY",
            joinColumns = @JoinColumn(name = "CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name = "BOOK_ID"))
    public List<Book> getBooks() {
        return Books;
    }

    public void setBooks(List<Book> books) {
        Books = books;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_Id")
    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long id) {
        this.categoryId = id;
    }

    public void setCategoryTitleRu(String titleRu) {
        this.categoryTitleRu = titleRu;
    }

    public void setCategoryTitleEn(String titleEn) {
        this.categoryTitleEn = titleEn;
    }

    @NotNull
    @Column(name = "category_Title_Ru")
    public String getCategoryTitleRu() {
        return categoryTitleRu;
    }

    @NotNull
    @Column(name = "category_Title_En")
    public String getCategoryTitleEn() {
        return categoryTitleEn;
    }

    @Override
    public String toString() {
        return String.valueOf(categoryId);
    }

    public Category(long CategoryId, String categoryTitleRu, String categoryTitleEn, List<Book> books) {
        this.categoryId = CategoryId;
        this.categoryTitleRu = categoryTitleRu;
        this.categoryTitleEn = categoryTitleEn;
        Books = books;
    }

    public Category(String categoryTitleRu, String categoryTitleEn) {
        this.categoryTitleRu = categoryTitleRu;
        this.categoryTitleEn = categoryTitleEn;
    }

    public Category() {
    }

}
