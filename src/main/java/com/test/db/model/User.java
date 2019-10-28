//package com.test.db.model;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//
//@Entity
//@Table(name = "USER")
//public class User {
//    private boolean enabled;
//    private Long id;
//    private String name;
//    private String email;
//    private String password;
////    private Role role;
//    private Basket basket;
//
//    @NotNull
//    @Column(name = "ENABLED")
//    public boolean isEnabled() {
//        return enabled;
//    }
//
//    public void setEnabled(boolean enabled) {
//        this.enabled = enabled;
//    }
//
//
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
//    @NotNull
//    @Column(name = "NAME")
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @NotNull
//    @Column(name = "EMAIL")
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    @NotNull
//    @Column(name = "PASSWORD")
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
////    @ManyToOne(targetEntity = Role.class, fetch = FetchType.LAZY)
////    @JoinColumn(name = "FK_ROLE_ID")
////    public Role getRole() {
////        return role;
////    }
////
////    public void setRole(Role role) {
////        this.role = role;
////    }
//
//    @OneToOne
//    @JoinColumn(name = "FK_BASKET_ID")
//    public Basket getBasket() {
//        return basket;
//    }
//
//    public void setBasket(Basket basket) {
//        this.basket = basket;
//    }
//
//    public User() {
//    }
//
//    public User(String name, String email, String password) {
//        this.name = name;
//        this.email = email;
//        this.password = password;
//    }
//
//
//}
