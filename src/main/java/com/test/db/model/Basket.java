//package com.test.db.model;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.Set;
//@Entity
//@Table(name = "BASKET")
//public class Basket {
//    private Long id;
//    private Set<Book> books;
//    private User user;
//    @Id
//    @Column(name = "ID")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    @ManyToMany
//    @JoinTable(name = "BOOK_WITH_BASKET",
//            joinColumns = @JoinColumn(name = "BASKET_ID"),
//            inverseJoinColumns = @JoinColumn(name="BOOK_ID"))
//    public Set<Book> getBooks() {
//        return books;
//    }
//
//    public void setBooks(Set<Book> books) {
//        this.books = books;
//    }
//    @OneToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name = "FK_User_ID")
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Basket() {
//    }
//
//}
