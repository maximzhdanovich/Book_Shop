//package com.test.db.model;
//
//import org.hibernate.annotations.LazyCollection;
//import org.hibernate.annotations.LazyCollectionOption;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//import java.io.Serializable;
//import java.util.Set;
//@Entity
//@Table(name = "ROLE")
//public class Role implements Serializable {
//    private Long id;
//    private String title;
////    private Set<User> users;
//
//    public Role() {
//    }
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
//    @NotNull
//    @Size(max = 1024)
//    @Column(name = "title")
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
////    @LazyCollection(LazyCollectionOption.TRUE)
////    @OneToMany(targetEntity = User.class, mappedBy = "role", orphanRemoval = true)
////    public Set<User> getUsers() {
////        return users;
////    }
////
////    public void setUsers(Set<User> users) {
////        this.users = users;
////    }
//
//    public Role(String title) {
//        this.title = title;
//    }
//}
