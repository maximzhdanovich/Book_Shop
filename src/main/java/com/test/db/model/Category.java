//package com.test.db.model;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//import java.io.Serializable;
//import java.util.Set;
//
//@Entity
//@Table(name = "CATEGORY")
//public class Category implements Serializable {
//
//    protected long id;
//    private String titleRu;
//    private String titleEn;
////    private Set<Book> Books;
//
//
//    public Category() {
//    }
//
////    @NotNull
////    @ManyToMany
////    @JoinTable(name = "BOOK_WITH_CATEGORY",
////            joinColumns = @JoinColumn(name = "CATEGORY_ID"),
////            inverseJoinColumns = @JoinColumn(name = "BOOK_ID"))
////    public Set<Book> getBooks() {
////        return Books;
////    }
////
////    public void setBooks(Set<Book> books) {
////        Books = books;
////    }
//
//    public Category(String titleRu, String titleEn, Set<Book> books) {
//        this.titleRu = titleRu;
//        this.titleEn = titleEn;
////        Books = books;
//    }
//
//    @Id
//    @Column(name = "ID")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    public long getId() {
//        return id;
//    }
//
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    @NotNull
//    @Size(max = 128)
//    @Column(name = "TITLE_RU")
//    public String getTitleRu() {
//        return titleRu;
//    }
//
//    public void setTitleRu(String titleRu) {
//        this.titleRu = titleRu;
//    }
//
//    public String getTitleEn() {
//        return titleEn;
//    }
//
//    @NotNull
//    @Size(max = 128)
//    @Column(name = "TITLE_EN")
//    public void setTitleEn(String titleEn) {
//        this.titleEn = titleEn;
//    }
//}
