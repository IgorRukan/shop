package com.example.springlog.service;

import com.example.springlog.model.User;

public interface UserService {
    void save(User user);
    User findByUsername(String username);
    void deleteUser(String id);
    String getCurrentUsername();
}
