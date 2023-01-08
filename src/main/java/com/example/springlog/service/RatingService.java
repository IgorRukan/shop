package com.example.springlog.service;

import com.example.springlog.model.Product;
import com.example.springlog.model.Rating;
import com.example.springlog.model.User;

import java.util.List;

public interface RatingService {
void save(long rate, Product product, User user);
    List<Rating> getAllRating(Product product);
}
