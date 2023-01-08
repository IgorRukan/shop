package com.example.springlog.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "userRate")
    private long userRate;
    @ManyToOne
    private Product product;
    @ManyToOne
    private User user;

    public Rating() {
    }

    public Rating(long userRate, Product product, User user) {
        this.userRate = userRate;
        this.product = product;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getUserRate() {
        return userRate;
    }

    public void setUserRate(long userRate) {
        this.userRate = userRate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
