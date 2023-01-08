package com.example.springlog.service.impl;

import com.example.springlog.model.Comment;
import com.example.springlog.model.Product;
import com.example.springlog.model.User;
import com.example.springlog.repository.CommentRepository;
import com.example.springlog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void save(String comment, User user, Product product){
        Comment com = new Comment(comment, user, product);
        commentRepository.save(com);
    }

    @Override
    public List<Comment> getAllComments(Product product) {
        return commentRepository.findAllByProduct(product);
    }
}
