package com.example.springlog.service.impl;

import com.example.springlog.model.Press;
import com.example.springlog.repository.PressRepository;
import com.example.springlog.service.PressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PressServiceImpl implements PressService {
    @Autowired
    private PressRepository pressRepository;

    @Override
    public void save(Press press) {
        pressRepository.save(press);
    }
    @Override
    public List<Press> getAll() {
        return pressRepository.findAll();
    }
}
