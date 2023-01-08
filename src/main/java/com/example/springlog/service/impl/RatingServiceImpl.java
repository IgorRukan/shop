package com.example.springlog.service.impl;

import com.example.springlog.model.Product;
import com.example.springlog.model.Rating;
import com.example.springlog.model.User;
import com.example.springlog.repository.ProductRepository;
import com.example.springlog.repository.RatingCategoriesRepository;
import com.example.springlog.repository.RatingRepository;
import com.example.springlog.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RatingCategoriesRepository ratingCategoriesRepository;
    @Override


    public void save(long rate, Product product, User user) {
        double finalRate = 0;
        double sum=0;
        List<Rating> all = ratingRepository.findAllByProduct(product);
        if(all.size() == 0){
            finalRate=rate;
        }else
            for (Rating i:all
                 ) {
                sum+=i.getUserRate();
            }
        finalRate = sum / all.size();
        product.setFinalRate(finalRate);
        productRepository.save(product);
    }

    @Override
    public List<Rating> getAllRating(Product product) {
        return ratingRepository.findAllByProduct(product);
    }
}
