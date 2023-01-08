package com.example.springlog.repository;

import com.example.springlog.model.Product;
import com.example.springlog.model.RatingCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingCategoriesRepository extends JpaRepository<RatingCategories,Long> {
    RatingCategories findByProduct(Product product);
}
