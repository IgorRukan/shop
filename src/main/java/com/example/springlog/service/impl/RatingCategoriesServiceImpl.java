package com.example.springlog.service.impl;

import com.example.springlog.model.Product;
import com.example.springlog.model.Rating;
import com.example.springlog.model.RatingCategories;
import com.example.springlog.model.User;
import com.example.springlog.repository.ProductRepository;
import com.example.springlog.repository.RatingCategoriesRepository;
import com.example.springlog.repository.RatingRepository;
import com.example.springlog.service.RatingCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingCategoriesServiceImpl implements RatingCategoriesService {
    @Autowired
    private RatingCategoriesRepository ratingCategoriesRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public void save(long rate, Product product, User user) {
        RatingCategories ratingCategories = ratingCategoriesRepository.findByProduct(product);
        Rating rating = new Rating(rate, product, user);
        int r = (int) rate;
        if (ratingRepository.findAllByProductAndUser(product, user).isEmpty()) {
            ratingRepository.save(rating);
        } else {
            Rating rating1 = ratingRepository.findRatingByProductAndUser(product,user);
            int prevRate =(int) rating1.getUserRate();
            refactor(ratingCategories,prevRate);
            rating1.setUserRate(rate);
            ratingRepository.save(rating1);
        }
        setRating(ratingCategories,r);
        ratingCategoriesRepository.save(ratingCategories);
    }
    public void setRating(RatingCategories ratingCategories, int r){
        switch (r) {
            case 1:
                ratingCategories.setOneRate(ratingCategories.getOneRate() + 1);
                break;
            case 2:
                ratingCategories.setTwoRate(ratingCategories.getTwoRate() + 1);
                break;
            case 3:
                ratingCategories.setThreeRate(ratingCategories.getThreeRate() + 1);
                break;
            case 4:
                ratingCategories.setFourRate(ratingCategories.getFourRate() + 1);
                break;
            case 5:
                ratingCategories.setFiveRate(ratingCategories.getFiveRate() + 1);
                break;
        }
    }
    public void refactor(RatingCategories ratingCategories,int prevRate){
        switch (prevRate) {
            case 1:
                ratingCategories.setOneRate(ratingCategories.getOneRate() - 1);
                break;
            case 2:
                ratingCategories.setTwoRate(ratingCategories.getTwoRate() - 1);
                break;
            case 3:
                ratingCategories.setThreeRate(ratingCategories.getThreeRate() - 1);
                break;
            case 4:
                ratingCategories.setFourRate(ratingCategories.getFourRate() - 1);
                break;
            case 5:
                ratingCategories.setFiveRate(ratingCategories.getFiveRate() - 1);
                break;
        }
    }

    public Object getAllRatingCategories(Product product) {
        return ratingCategoriesRepository.findByProduct(product);
    }
}
