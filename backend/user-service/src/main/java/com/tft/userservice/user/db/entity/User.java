package com.tft.userservice.user.db.entity;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 사용자 시퀀스
    private Long userId;

    // 사용자 아이디
    private String loginId;

    // 사용자 패스워드
    private String loginPw;

    // 사용자 이름
    private String userName;

    private String nickName;

    private String phone;

    private String currentAddress;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(columnDefinition = "TINYINT(1)")
    private boolean sex;

    private int age;

    private String role;

    @Column(columnDefinition = "TINYINT(1)")
    @ColumnDefault("False")
    private boolean pay;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Bill> bills;

    @ColumnDefault("0")
    private Long storeId;

    @Builder
    public User(Long userId, String loginId, String loginPw, String userName, String nickName, String phone,
                String address, LocalDateTime createdDate, boolean sex, int age, String role, boolean pay) {
        this.userId = userId;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.userName = userName;
        this.nickName = nickName;
        this.phone = phone;
        this.currentAddress = address;
        this.sex = sex;
        this.age = age;
        this.role = role;
        this.pay = pay;
    }

    public void changePay(boolean newPay){
        this.pay = newPay;
    }

    public void changeStoreId(Long newStoreId){
        this.storeId = newStoreId;
    }
}
