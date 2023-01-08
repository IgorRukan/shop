package com.example.springlog.controller;

import com.example.springlog.model.Image;
import com.example.springlog.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
public class ImageController {

    private ImageRepository imageRepo;

    @Autowired
    public void setImageRepo(ImageRepository imageRepo) {
        this.imageRepo = imageRepo;
    }

    @GetMapping("/images/{id}")
    private ResponseEntity<?> getImageById(@PathVariable Long id){
        Image image = imageRepo.findById(id).orElse(null);
        assert image != null;
        return ResponseEntity.ok()
                .header("fileName",image.getOriginalFile())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));

    }
}
