package com.tft.userservice.user.db.repository;

import com.tft.userservice.user.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginId(String account);
    Optional<User> findByUserId(Long userId);
}
