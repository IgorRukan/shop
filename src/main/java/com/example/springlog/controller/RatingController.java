package com.example.springlog.controller;

import com.example.springlog.model.Product;
import com.example.springlog.model.User;
import com.example.springlog.service.ProductService;
import com.example.springlog.service.RatingService;
import com.example.springlog.service.UserService;
import com.example.springlog.service.impl.RatingCategoriesServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RatingController {
    public final RatingService ratingService;
    public final ProductService productService;
    public final UserService userService;
    public final RatingCategoriesServiceImpl ratingCategoriesService;

    public RatingController(RatingService ratingService, ProductService productService, UserService userService, RatingCategoriesServiceImpl ratingCategoriesService) {
        this.ratingService = ratingService;
        this.productService = productService;
        this.userService = userService;
        this.ratingCategoriesService = ratingCategoriesService;
    }

    @GetMapping("/home/{product_id}/rate")
    public String rate(@PathVariable(value = "product_id") long id, @RequestParam int rate){
        String username = userService.getCurrentUsername();
        User user = userService.findByUsername(username);
        ratingCategoriesService.save(rate,productService.getById(id),user);
        ratingService.save(rate,productService.getById(id),user);
        return "redirect:/home/"+id;
    }
}
