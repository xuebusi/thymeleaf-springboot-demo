package com.xuebusi.thymeleaf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuebusi.thymeleaf.model.Product;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductMapper extends BaseMapper<Product> {

    @Select("SELECT DISTINCT category FROM products")
    List<String> selectAllCategories();

    @Select("SELECT * FROM products WHERE featured = 1")
    List<Product> selectFeaturedProducts();
}