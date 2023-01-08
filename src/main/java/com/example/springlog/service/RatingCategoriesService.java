package com.example.springlog.service;

import com.example.springlog.model.Product;
import com.example.springlog.model.User;

public interface RatingCategoriesService {
    void save(long rate, Product product, User user);
    Object getAllRatingCategories(Product product);
}
