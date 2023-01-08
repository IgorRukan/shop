package com.example.springlog.controller;

import com.example.springlog.model.*;
import com.example.springlog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class ProductController {

    public final ProductService iProduct;
    public final UserService userService;
    public final CartService cartService;
    public final RatingService ratingService;
    public final RatingCategoriesService ratingCategoriesService;
    public final CommentService commentService;

    @Autowired
    public ProductController(ProductService iProduct, UserService userService, CartService cartService, RatingService ratingService, RatingCategoriesService ratingCategoriesService, CommentService commentService) throws IOException {
        this.iProduct = iProduct;
        this.userService = userService;
        this.cartService = cartService;
        this.ratingService = ratingService;
        this.ratingCategoriesService = ratingCategoriesService;
        this.commentService = commentService;
    }

    @GetMapping("/home")
    public String getAllProducts(Model model) {
        model.addAttribute("product", iProduct.getAllProducts());
        return "home";
    }

    @GetMapping("/home/addProduct")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "product-add";
    }

    @PostMapping("/home/addProduct")
    public String addProduct(@ModelAttribute("product") Product product,
                             @RequestParam MultipartFile file1,
                             @RequestParam MultipartFile file2,
                             @RequestParam MultipartFile file3) throws IOException {
        iProduct.saveProduct(product, file1, file2, file3);
        return "redirect:/home";
    }

    @GetMapping("/home/{id}")
    public String productPage(@PathVariable(value = "id") long id, Model model) {
        Product product = iProduct.getById(id);
        model.addAttribute("product", product);
        model.addAttribute("images", product.getImages());
        model.addAttribute("rating",ratingService.getAllRating(product));
        model.addAttribute("ratingCategories",ratingCategoriesService.getAllRatingCategories(product));
        model.addAttribute("comments",commentService.getAllComments(product));
        return "product-page";
    }

    @GetMapping("/home/{product_id}/edit")
    public String edProduct(@PathVariable(value = "product_id") long id, Model model) {
        Product product = iProduct.getById(id);
        model.addAttribute("product", product);
        return "product-edit";
    }

    @PostMapping("/home/{product_id}/edit")
    public String editProduct(@ModelAttribute("product") Product product,
                              @PathVariable(value = "product_id") long id,
                              @RequestParam MultipartFile file1,
                              @RequestParam MultipartFile file2,
                              @RequestParam MultipartFile file3
    ) throws IOException {
        Product productUpdated = iProduct.getById(id);
        iProduct.updateProduct(productUpdated, product, file1, file2, file3);
        return "redirect:/home";
    }

    @PostMapping("/home/{product_id}/delete")
    public String delete(@PathVariable(value = "product_id") long id) {
        iProduct.deleteProductById(id);
        return "redirect:/home";
    }

    @GetMapping("/home/find-by-name")
    public String findProduct(@RequestParam String name, Model model) {
        Product product = iProduct.findByName(name);
        model.addAttribute("product", product);
        model.addAttribute("images", product.getImages());
        return "product-page";
    }
    @GetMapping("home/category/{type}")
    public String category(@PathVariable (value = "type") String type,Model model){
        model.addAttribute("product",iProduct.findByType(type));
        return "category";
    }

    @PostMapping("/home/{product_id}/order")
    public String order(@PathVariable(value = "product_id") long id, Model model) {
        String username = userService.getCurrentUsername();
        User user = userService.findByUsername(username);
        cartService.addToCart(user, iProduct.getById(id));
        return "redirect:/home";
    }
    @GetMapping("/home/sort")
    public String sort(@RequestParam String sortParam, Model model){
        model.addAttribute("product", iProduct.sort(sortParam));
        return "home";
    }
}
