package com.example.springlog.controller;

import com.example.springlog.model.Cart;
import com.example.springlog.model.Product;
import com.example.springlog.model.User;
import com.example.springlog.service.CartService;
import com.example.springlog.service.ProductService;
import com.example.springlog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    public final ProductService iProduct;
    public final UserService userService;
    public final CartService cartService;

    public CartController(ProductService iProduct, UserService userService, CartService cartService) {
        this.iProduct = iProduct;
        this.userService = userService;
        this.cartService = cartService;
    }

    @GetMapping("/home/cart")
    public String cart(Model model){
        User user = userService.findByUsername(userService.getCurrentUsername());
        model.addAttribute("cart",user.getCart());
        model.addAttribute("song","https://res.cloudinary.com/dt7vphaze/video/upload/v1620934652/nc.Hip-Hope/SaintJHN/audio/SAINt_JHN_-_Trap_feat._Lil_Baby_pnaibh.mp3");
        return "cart";
    }

    @PostMapping("/home/cart/{orderNumber}")
    public String order(@PathVariable (value = "orderNumber")String orderNumber ,@RequestParam String amount, Model model){
        int am = Integer.parseInt(amount);
        Cart cart = cartService.getByOrderNumber(orderNumber);
        if (am<1 || am>10 || amount.isEmpty()){
            model.addAttribute("amountError", "You must select at least one");
            return "cart";
        }
        cart.setStatus("ordered");
        cart.setAmount(amount);
        cartService.order(cart);
        return "redirect:/home/cart";
    }
    @PostMapping("/deleteOrder/{id}")
    public String delete(@PathVariable(value = "id") Long id){
        cartService.deleteOrder(id);
        return "redirect:/home/cart";
    }
}
