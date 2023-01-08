package com.example.springlog.service;

import com.example.springlog.model.Comment;
import com.example.springlog.model.Product;
import com.example.springlog.model.User;

import java.util.List;

public interface CommentService {
    public void save(String com, User user, Product product);

    List<Comment> getAllComments(Product product);
}
