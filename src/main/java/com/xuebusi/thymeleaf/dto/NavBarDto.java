package com.xuebusi.thymeleaf.dto;

import com.xuebusi.thymeleaf.model.NavItem;
import com.xuebusi.thymeleaf.model.User;
import lombok.Data;

import java.util.List;

@Data
public class NavBarDto {
    private String appName;
    private User currentUser;
    private List<NavItem> mainNavItems;
    private List<NavItem> userNavItems;
    private Integer notificationCount;

    public boolean isAuthenticated() {
        return currentUser != null;
    }
}