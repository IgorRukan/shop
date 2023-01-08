package com.example.springlog.service.impl;

import com.example.springlog.model.Cart;
import com.example.springlog.model.Product;
import com.example.springlog.model.User;
import com.example.springlog.repository.CartRepository;
import com.example.springlog.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public void addToCart(User user, Product product) {
        String num = "1" + user.getId() + product.getProduct_id();
        cartRepository.save(new Cart(num, "in cart", Collections.singleton(product), user));
    }
    @Override
    public void order(Cart cart){
        cartRepository.save(cart);
    }
    @Override
    public Cart getByOrderNumber(String orderNumber){return cartRepository.findByOrderNumber(orderNumber);};

    @Override
    public List<Cart> getAllOrders(User user) {
        return cartRepository.findAllByUser(user);
    }

    @Override
    public void deleteOrder(Long id) {
        cartRepository.deleteById(id);
    }
}
