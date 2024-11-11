package com.actvn.Shopee_BE.service.impl;

import com.actvn.Shopee_BE.repository.UserRepository;
import com.actvn.Shopee_BE.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
