package com.xuebusi.thymeleaf.service.impl;

import com.xuebusi.thymeleaf.model.Product;
import com.xuebusi.thymeleaf.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    // 模拟数据库中的产品
    private final List<Product> products = List.of(
            new Product(1L, "笔记本电脑", "高性能商务笔记本",
                    new BigDecimal("5999.99"), 85, "电子产品", 4.5, true),
            new Product(2L, "无线耳机", "降噪蓝牙耳机",
                    new BigDecimal("399.00"), 120, "电子产品", 4.8, true),
            new Product(3L, "编程书籍", "Java编程指南",
                    new BigDecimal("89.50"), 76, "图书", 4.2, false),
            new Product(4L, "办公椅", "人体工学办公椅",
                    new BigDecimal("450.00"), 42, "家具", 4.7, true)
    );

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public List<Product> getFeaturedProducts() {
        return products.stream()
                .filter(Product::getFeatured)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }
}