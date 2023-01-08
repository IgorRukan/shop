package com.example.springlog.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long product_id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "price")
    private String price;
    @Column(name = "description")
    private String description;
    @Column(name = "finalRate")
    private double finalRate;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,
            mappedBy = "product")
    private List<Image> images = new ArrayList<>();
    private Long previewImageId;
    private LocalDateTime localDateTime;

    @ManyToMany
    private List<Cart> cart;

    @OneToMany(mappedBy = "product")
    private Set<Rating> rating;

    @OneToOne(mappedBy = "product")
    private RatingCategories ratingCategories;

    @OneToMany(mappedBy = "product")
    private List<Comment> comments;

    @PrePersist
    private void init(){
        localDateTime = LocalDateTime.now();
    }

    public void addImageToProduct(Image image){
        image.setProduct(this);
        images.add(image);
    }

    public Product(String name, String type, String price, String description) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.description = description;
    }

    public Product() {

    }

    public Set<Rating> getRating() {
        return rating;
    }

    public void setRating(Set<Rating> rating) {
        this.rating = rating;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Long getPreviewImageId() {
        return previewImageId;
    }

    public void setPreviewImageId(Long previewImageId) {
        this.previewImageId = previewImageId;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Cart> getCart() {
        return cart;
    }

    public void setCart(List<Cart> cart) {
        this.cart = cart;
    }

    public double getFinalRate() {
        return finalRate;
    }

    public void setFinalRate(double finalRate) {
        this.finalRate = finalRate;
    }

    public RatingCategories getRatingCategories() {
        return ratingCategories;
    }

    public void setRatingCategories(RatingCategories ratingCategories) {
        this.ratingCategories = ratingCategories;
    }
}
