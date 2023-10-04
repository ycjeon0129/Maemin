package com.tft.userservice.user.db.repository;

import com.tft.userservice.user.db.entity.Bill;
import com.tft.userservice.user.db.entity.BillMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillMenuRepository extends JpaRepository<BillMenu, Long> {

}
