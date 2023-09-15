package com.tft.userservice.user.dto.request;

import com.tft.userservice.user.db.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class JoinReq {


    private String loginId;
    private String loginPw;
    private String userName;
    private String nickName;
    private String phone;
    private boolean sex; //-> 0=남자 / 1=여자
    private int age;
    private String role; // ROLE_CUSTOMER or ROLE_OWNER

    // 사용자에게 입력받은 데이터를 Entity로 보내줌
    public User toEntity(String password){          // 비밀번호를 암호화 해야하기 때문에 password는 따로 입력받아 저장시킨다.
        return User.builder()
                .loginId(this.loginId)
                .loginPw(password)
                .userName(this.userName)
                .nickName(this.nickName)
                .phone(this.phone)
                .sex(this.sex)
                .age(this.age)
                .role(this.role).build();
    }
}
