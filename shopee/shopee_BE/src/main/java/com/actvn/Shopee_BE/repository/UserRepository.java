package com.actvn.Shopee_BE.repository;

import com.actvn.Shopee_BE.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String username);
}
