package com.xuebusi.thymeleaf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuebusi.thymeleaf.mapper.ProductMapper;
import com.xuebusi.thymeleaf.model.Product;
import com.xuebusi.thymeleaf.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public List<Product> getAllProducts() {
        return productMapper.selectList(null);
    }

    @Override
    public List<Product> getFeaturedProducts() {
        return productMapper.selectFeaturedProducts();
    }

    @Override
    public Product getProductById(Long id) {
        return productMapper.selectById(id);
    }

    @Override
    public List<String> getAllCategories() {
        return productMapper.selectAllCategories();
    }

    @Override
    public Page<Product> getProductsByPage(int page, int size) {
        Page<Product> pageInfo = new Page<>(page, size);
        return productMapper.selectPage(pageInfo, null);
    }

    @Override
    public Page<Product> getProductsByCategory(String category, int page, int size) {
        Page<Product> pageInfo = new Page<>(page, size);
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category", category);
        return productMapper.selectPage(pageInfo, queryWrapper);
    }
}