package com.xuebusi.thymeleaf.service;

import com.xuebusi.thymeleaf.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();

    List<Product> getFeaturedProducts();

    Optional<Product> getProductById(Long id);
}