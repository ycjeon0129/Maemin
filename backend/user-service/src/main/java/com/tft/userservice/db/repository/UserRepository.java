package com.tft.userservice.db.repository;

import com.tft.userservice.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAccount(String account);
}
