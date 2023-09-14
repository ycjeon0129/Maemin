package com.tft.userservice.api.dto.request;

import com.tft.userservice.db.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class JoinReq {
    private String account;
    private String password;
    private String email;

    // 사용자에게 입력받은 데이터를 Entity로 보내줌
    public User toEntity(String password){          // 비밀번호를 암호화 해야하기 때문에 password는 따로 입력받아 저장시킨다.
        return User.builder()
                .account(this.account)
                .userPw(password)
                .build();
    }
}
