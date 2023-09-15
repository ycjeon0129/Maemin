package com.tft.userservice.user.service;

import com.tft.userservice.user.dto.request.JoinReq;
import com.tft.userservice.user.db.entity.User;
import com.tft.userservice.user.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    // 회원가입 기능
    // 데이터가 없을경우 정상동작, 데이터가 이미 있을겨우 오류 발생(회원가입 불가)
    // 유저에게 입력받은 데이터 중복 검사 및 DB 저장
    public String join(JoinReq request){

//        userRepository.findByAccount(request.getAccount())
//                // 내가 원하는 에러코드를 만들어서 설정하기
//                // enum클래스를 통해 미리 설정해둔 에러구조를 통해 에러를 넘겨준다.
//                .ifPresent(user -> {
//                    throw new HospitalReviewAppException(ErrorCode.DUPLICATED_USER_NAME,String.format("Username :"+request.getUserName()));
//                });


        // 비밀번호 암호화 하는방식 2가지
        //  1. 내가 설정한 Config파일인 EncrypterConfig를 DI를받아 사용하는 법
        //     EncrypterConfig의 메서드인 encodePwd()를 호출하고 BCryptPasswordEncoder안에 있는 encode() 기능 사용
//        User saveUser = userRepository.save(request.toEntity(encrypterConfig.encodePwd().encode(request.getPassword())));

        // 2. 기존 클래스인 BCryptPasswordEncoder를 DI를 받아 사용하는 법
        //     BCryptPasswordEncoder클래스안에 있는 메서드 encode() 기능 사용 => 자동으로 EncrypterConfig Bean과 연결됨
        User saveUser2 = userRepository.save(request.toEntity(encoder.encode(request.getLoginPw())));    // UserJoinRequest -> User Entity변환후 데이터 DB 저장 , password는 암호화 하여 저장

//        return UserDto.fromEntity(saveUser2);    // User에게 입력받아 회원가입한 데이터를 UserDto에 저장함
        return "회원가입 성공";
    }
}
