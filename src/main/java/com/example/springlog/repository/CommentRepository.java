package com.example.springlog.repository;

import com.example.springlog.model.Comment;
import com.example.springlog.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByProduct(Product product);
}
