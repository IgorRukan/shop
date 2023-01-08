package com.example.springlog.service;

import com.example.springlog.model.Press;
import com.example.springlog.model.User;

import java.util.List;

public interface PressService {
    void save(Press press);

    List<Press> getAll();
}
