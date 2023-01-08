package com.example.springlog.service;

import com.example.springlog.model.Cart;
import com.example.springlog.model.Product;
import com.example.springlog.model.User;

import java.util.List;

public interface CartService {
    void addToCart(User user, Product product);

    Cart getByOrderNumber(String orderNumber);

    void order(Cart cart);

    List<Cart> getAllOrders(User user);

    void deleteOrder(Long id);
}
