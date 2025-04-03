package com.xuebusi.thymeleaf.service.impl;

import com.xuebusi.thymeleaf.model.User;
import com.xuebusi.thymeleaf.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    // 模拟数据库中的用户
    private final User demoUser = new User(
            1L,
            "tech_guru",
            "张技术",
            "tech@example.com",
            "/images/avatar1.png",
            3, // 专业会员
            LocalDateTime.now().minusDays(45),
            true,
            List.of("ROLE_USER", "ROLE_ADMIN")
    );

    @Override
    public Optional<User> getCurrentUser() {
        // 实际项目中应从安全上下文获取当前用户
        return Optional.of(demoUser);
    }

    @Override
    public List<String> getUserRoles(Long userId) {
        return demoUser.getRoles();
    }
}