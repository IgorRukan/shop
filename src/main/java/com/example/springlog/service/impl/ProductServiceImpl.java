package com.example.springlog.service.impl;

import com.example.springlog.model.Image;
import com.example.springlog.model.Product;
import com.example.springlog.model.RatingCategories;
import com.example.springlog.repository.ProductRepository;
import com.example.springlog.repository.RatingCategoriesRepository;
import com.example.springlog.service.ProductService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepo;
    RatingCategoriesRepository ratingCategoriesRepository;

    public ProductServiceImpl(ProductRepository productRepo, RatingCategoriesRepository ratingCategoriesRepository) {
        this.productRepo = productRepo;
        this.ratingCategoriesRepository = ratingCategoriesRepository;
    }

    public Product saveProduct(Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        product = AddImg(product, file1, file2, file3);
        product.setFinalRate(0);
        RatingCategories ratingCategories = new RatingCategories(0,0,0,0,0,product);
        Product productFromDb = productRepo.save(product);
        productFromDb.setPreviewImageId(productFromDb.getImages().get(0).getId());
        ratingCategoriesRepository.save(ratingCategories);
        return productRepo.save(product);
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setSize(file.getSize());
        image.setOriginalFile(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setBytes(file.getBytes());
        return image;
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product getById(Long id) {
        return productRepo.findById(id).orElseThrow(null);
    }

    @Override
    public Product updateProduct(Product productUpdated, Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        productUpdated.setType(product.getType());
        productUpdated.setDescription(product.getDescription());
        productUpdated.setName(product.getName());
        productUpdated.setPrice(product.getPrice());
        productUpdated = AddImg(productUpdated, file1, file2, file3);
        Product productFromDb = productRepo.save(productUpdated);
        productFromDb.setPreviewImageId(productFromDb.getImages().get(0).getId());
        return productRepo.save(productUpdated);
    }

    public Product AddImg(Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        Image image1;
        Image image2;
        Image image3;
        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            product.addImageToProduct(image1);
        }
        if (file2.getSize() != 0) {
            image2 = toImageEntity(file2);
            product.addImageToProduct(image2);
        }
        if (file3.getSize() != 0) {
            image3 = toImageEntity(file3);
            product.addImageToProduct(image3);
        }
        return product;
    }

    public void deleteProductById(Long id) {
        productRepo.deleteById(id);
    }

    public Product findByName(String name) {
        return productRepo.findByName(name);
    }

    public List <Product> findByType(String type) {return productRepo.findAllByType(type); }

    @Override
    public Object sort(String s) {
        switch (s) {
            case "name_ascending":
                return productRepo.findAll(Sort.by("name").ascending());
            case "name_descending":
                return productRepo.findAll(Sort.by("name").descending());
            case "type_ascending":
                return productRepo.findAll(Sort.by("type").ascending());
            case "type_descending":
                return productRepo.findAll(Sort.by("type").descending());
            case "price_ascending":
                return productRepo.findAll(Sort.by("price").ascending());
            case "price_descending":
                return productRepo.findAll(Sort.by("price").descending());
            default:
                return productRepo;
        }
    }
}
