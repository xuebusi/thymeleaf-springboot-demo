package com.xuebusi.thymeleaf.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuebusi.thymeleaf.dto.NavBarDto;
import com.xuebusi.thymeleaf.model.NavItem;
import com.xuebusi.thymeleaf.model.Product;
import com.xuebusi.thymeleaf.service.ProductService;
import com.xuebusi.thymeleaf.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    @Resource
    private ProductService productService;

    @Resource
    private UserService userService;

    @GetMapping("/products")
    public String listProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "8") int size,
            @RequestParam(required = false) String category,
            Model model) {

        // 构建导航栏数据
        NavBarDto navBar = createNavBarData("products");

        Page<Product> productPage;
        if (category != null && !category.isEmpty()) {
            productPage = productService.getProductsByCategory(category, page, size);
        } else {
            productPage = productService.getProductsByPage(page, size);
        }

        model.addAttribute("navBar", navBar);
        model.addAttribute("pageTitle", "产品列表");
        model.addAttribute("productPage", productPage); // ✅ 正确传递
        model.addAttribute("products", productPage.getRecords()); // ✅ 直接传递列表
        model.addAttribute("categories", productService.getAllCategories());

        return "products";
    }

    @GetMapping("/products/{id}")
    public String productDetail(@PathVariable Long id, Model model) {
        // 构建导航栏数据
        NavBarDto navBar = createNavBarData("products");

        Product product = productService.getProductById(id);
        if (product != null) {
            model.addAttribute("product", product);
            model.addAttribute("pageTitle", product.getName());
        }

        model.addAttribute("navBar", navBar);
        return "product-detail";
    }

    private NavBarDto createNavBarData(String activeItem) {
        // 与HomeController中的方法类似，实际项目中可以提取到基类或工具类
        NavBarDto navBar = new NavBarDto();
        navBar.setAppName("Thymeleaf演示");
        userService.getCurrentUser().ifPresent(navBar::setCurrentUser);
        navBar.setMainNavItems(List.of(
                new NavItem("首页", "/", "fa-home", "home".equals(activeItem)),
                new NavItem("产品", "/products", "fa-shopping-basket", "products".equals(activeItem)),
                new NavItem("关于", "/about", "fa-info-circle", "about".equals(activeItem))
        ));
        // 设置用户导航项...
        return navBar;
    }
}