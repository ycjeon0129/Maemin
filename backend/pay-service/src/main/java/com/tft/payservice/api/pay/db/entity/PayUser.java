package com.tft.payservice.api.pay.db.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@Table(name = "pay_user")
@Entity
public class PayUser {

    @Id
    @NotNull
    private Long userId;

    @NotNull
    private String payPw;   // salt 및 key streching 적용값

    private String salt;

    private int keyStreching;   // 1 ~ 5 사이 랜덤값

    @OneToMany(mappedBy = "payUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pay> pays = new ArrayList<>();

}
