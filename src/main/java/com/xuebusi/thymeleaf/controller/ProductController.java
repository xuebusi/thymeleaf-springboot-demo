package com.xuebusi.thymeleaf.controller;

import com.xuebusi.thymeleaf.dto.NavBarDto;
import com.xuebusi.thymeleaf.model.NavItem;
import com.xuebusi.thymeleaf.model.Product;
import com.xuebusi.thymeleaf.service.ProductService;
import com.xuebusi.thymeleaf.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    private final ProductService productService;
    private final UserService userService;

    public ProductController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/products")
    public String listProducts(Model model) {
        // 构建导航栏数据
        NavBarDto navBar = createNavBarData("products");

        // 获取产品列表和分类
        List<Product> products = productService.getAllProducts();
        Set<String> categories = products.stream()
                .map(Product::getCategory)
                .collect(Collectors.toSet());

        // 添加模型属性
        model.addAttribute("navBar", navBar);
        model.addAttribute("pageTitle", "产品列表");
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);

        return "products";
    }

    @GetMapping("/products/{id}")
    public String productDetail(@PathVariable Long id, Model model) {
        // 构建导航栏数据
        NavBarDto navBar = createNavBarData("products");

        // 获取产品详情
        Optional<Product> product = productService.getProductById(id);
        if (!product.isPresent()) {
            return "redirect:/products";
        }

        // 添加模型属性
        model.addAttribute("navBar", navBar);
        model.addAttribute("pageTitle", product.get().getName());
        model.addAttribute("product", product.get());

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