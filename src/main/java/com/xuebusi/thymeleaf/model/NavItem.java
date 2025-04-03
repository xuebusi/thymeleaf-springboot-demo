package com.xuebusi.thymeleaf.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NavItem {
    private String text;
    private String url;
    private String icon;
    private Boolean active;
}