package com.example.springlog.repository;

import com.example.springlog.model.Product;
import com.example.springlog.model.Rating;
import com.example.springlog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {
    Optional<Rating> findByUserRate(long userRate);
    Rating findRatingByProductAndUser(Product product,User user);
    List<Rating> findAllByProduct(Product product);
    List<Rating> findAllByProductAndUser(Product product, User user);
    List<Rating> findAllByProductAndUserRate(Product product, long userRate);
}
