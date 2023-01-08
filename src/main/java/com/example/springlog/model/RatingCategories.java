package com.example.springlog.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "raitngCategories")
public class RatingCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "fiveRate")
    private int fiveRate;
    @Column(name = "fourRate")
    private int fourRate;
    @Column(name = "threeRate")
    private int threeRate;
    @Column(name = "twoRate")
    private int twoRate;
    @Column(name = "oneRate")
    private int oneRate;
    @OneToOne
    private Product product;

    public RatingCategories() {
    }

    public RatingCategories(int fiveRate, int fourRate, int threeRate, int twoRate, int oneRate, Product product) {
        this.fiveRate = fiveRate;
        this.fourRate = fourRate;
        this.threeRate = threeRate;
        this.twoRate = twoRate;
        this.oneRate = oneRate;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getFiveRate() {
        return fiveRate;
    }

    public void setFiveRate(int fiveRate) {
        this.fiveRate = fiveRate;
    }

    public int getFourRate() {
        return fourRate;
    }

    public void setFourRate(int fourRate) {
        this.fourRate = fourRate;
    }

    public int getThreeRate() {
        return threeRate;
    }

    public void setThreeRate(int threeRate) {
        this.threeRate = threeRate;
    }

    public int getTwoRate() {
        return twoRate;
    }

    public void setTwoRate(int twoRate) {
        this.twoRate = twoRate;
    }

    public int getOneRate() {
        return oneRate;
    }

    public void setOneRate(int oneRate) {
        this.oneRate = oneRate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
