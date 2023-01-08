package com.example.springlog.service;

import com.example.springlog.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface ProductService {
    Product saveProduct(Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException;

    List<Product> getAllProducts();

    Product getById(Long id);

    Product updateProduct(Product productToBeUpdated, Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException;

    void deleteProductById(Long id);

    Product findByName(String name);

    List <Product> findByType(String type);

    Object sort(String s);
}
