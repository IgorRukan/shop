package com.example.springlog.repository;

import com.example.springlog.model.Cart;
import com.example.springlog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    List<Cart> findAllByUser(User user);
    Cart findByOrderNumber(String orderNumber);
}
