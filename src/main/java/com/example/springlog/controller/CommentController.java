package com.example.springlog.controller;

import com.example.springlog.model.Product;
import com.example.springlog.model.User;
import com.example.springlog.service.CommentService;
import com.example.springlog.service.ProductService;
import com.example.springlog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {

    public final CommentService commentService;
    public final UserService userService;
    public final ProductService productService;

    public CommentController(CommentService commentService, UserService userService, ProductService productService) {
        this.commentService = commentService;
        this.userService = userService;
        this.productService = productService;
    }

    @PostMapping("/home/{product_id}/comment")
    public String comment(@RequestParam(value = "comment") String comment, @PathVariable(value = "product_id") long id){
        User user = userService.findByUsername(userService.getCurrentUsername());
        Product product = productService.getById(id);
        commentService.save(comment,user,product);
        return "redirect:/home/"+id;
    }
}
