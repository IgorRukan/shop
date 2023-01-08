package com.example.springlog.controller;

import com.example.springlog.model.Press;
import com.example.springlog.model.Product;
import com.example.springlog.service.PressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import java.io.IOException;

@Controller
public class PressController {
    private final PressService pressService;

    public PressController(PressService pressService) {
        this.pressService = pressService;
    }

    @GetMapping("/press")
    public String press(Model model) {
        model.addAttribute("list", pressService.getAll());
        model.addAttribute("press",new Press());
        return "press";
    }

    @PostMapping("/press")
    public String add(@ModelAttribute("press") Press press, Model model) {
        pressService.save(press);
        return "redirect:/press";
    }
}
