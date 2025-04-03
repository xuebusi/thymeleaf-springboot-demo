package com.xuebusi.thymeleaf.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String fullName;
    private String email;
    private String avatar;
    private Integer memberLevel; // 1-普通, 2-高级, 3-专业
    private LocalDateTime registerTime;
    private Boolean active;
    private List<String> roles;

    // 获取注册天数
    public long getRegisterDays() {
        return java.time.temporal.ChronoUnit.DAYS.between(
                registerTime,
                LocalDateTime.now()
        );
    }
}