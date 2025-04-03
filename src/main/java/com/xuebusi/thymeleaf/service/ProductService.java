package com.xuebusi.thymeleaf.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuebusi.thymeleaf.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    List<Product> getFeaturedProducts();

    Product getProductById(Long id);

    List<String> getAllCategories();

    Page<Product> getProductsByPage(int page, int size);

    Page<Product> getProductsByCategory(String category, int page, int size);
}