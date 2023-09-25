package com.tft.userservice.user.db.repository;

import com.tft.userservice.user.db.entity.Bill;
import com.tft.userservice.user.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BillRepository extends JpaRepository<Bill, Long> {

}
