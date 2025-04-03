package com.xuebusi.thymeleaf.service;

import com.xuebusi.thymeleaf.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getCurrentUser();

    List<String> getUserRoles(Long userId);
}