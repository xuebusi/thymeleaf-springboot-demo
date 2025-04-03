package com.xuebusi.thymeleaf.controller;

import com.xuebusi.thymeleaf.dto.NavBarDto;
import com.xuebusi.thymeleaf.model.NavItem;
import com.xuebusi.thymeleaf.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Model model, HttpServletRequest request) {
        String path = request.getRequestURI();
        model.addAttribute("currentPath", path);
        // 构建导航栏数据
        NavBarDto navBar = createNavBarData("home");

        // 添加模型属性
        model.addAttribute("navBar", navBar);
        model.addAttribute("pageTitle", "首页");
        model.addAttribute("welcomeMessage", "欢迎来到我们的网站");

        return "home";
    }

    private NavBarDto createNavBarData(String activeItem) {
        NavBarDto navBar = new NavBarDto();
        navBar.setAppName("Thymeleaf演示");

        // 设置当前用户
        userService.getCurrentUser().ifPresent(navBar::setCurrentUser);

        // 主导航项
        navBar.setMainNavItems(List.of(
                new NavItem("首页", "/", "fa-home", "home".equals(activeItem)),
                new NavItem("产品", "/products", "fa-shopping-basket", "products".equals(activeItem)),
                new NavItem("关于", "/about", "fa-info-circle", "about".equals(activeItem))
        ));

        // 用户导航项
        if (navBar.isAuthenticated()) {
            navBar.setUserNavItems(List.of(
                    new NavItem("个人资料", "/profile", "fa-user", false),
                    new NavItem("设置", "/settings", "fa-cog", false),
                    new NavItem("退出", "/logout", "fa-sign-out-alt", false)
            ));
            navBar.setNotificationCount(3);
        } else {
            navBar.setUserNavItems(List.of(
                    new NavItem("登录", "/login", "fa-sign-in-alt", false),
                    new NavItem("注册", "/register", "fa-user-plus", false)
            ));
        }

        return navBar;
    }
}