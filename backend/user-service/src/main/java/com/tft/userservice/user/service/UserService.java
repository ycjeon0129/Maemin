package com.tft.userservice.user.service;

import com.tft.userservice.common.exception.custom.LoginIdExistException;
import com.tft.userservice.common.exception.custom.UserNotExistException;
import com.tft.userservice.user.db.entity.Bill;
import com.tft.userservice.user.db.entity.BillMenu;
import com.tft.userservice.user.db.repository.BillMenuRepository;
import com.tft.userservice.user.db.repository.BillRepository;
import com.tft.userservice.user.dto.request.BillAddReq;
import com.tft.userservice.user.dto.request.JoinReq;
import com.tft.userservice.user.db.entity.User;
import com.tft.userservice.user.db.repository.UserRepository;
import com.tft.userservice.user.dto.response.BillAddRes;
import com.tft.userservice.user.dto.response.BillRes;
import com.tft.userservice.user.dto.response.IdCheckRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BillRepository billRepository;
    private final BillMenuRepository billMenuRepository;

    private final BCryptPasswordEncoder encoder;

    // 회원가입 기능
    // 데이터가 없을경우 정상동작, 데이터가 이미 있을겨우 오류 발생(회원가입 불가)
    // 유저에게 입력받은 데이터 중복 검사 및 DB 저장
    public String join(JoinReq request) {

        userRepository.findByLoginId(request.getLoginId())
                // 내가 원하는 에러코드를 만들어서 설정하기
                // enum클래스를 통해 미리 설정해둔 에러구조를 통해 에러를 넘겨준다.
                .ifPresent(user -> {
                    throw new LoginIdExistException();
                });

        // 2. 기존 클래스인 BCryptPasswordEncoder를 DI를 받아 사용하는 법
        //     BCryptPasswordEncoder클래스안에 있는 메서드 encode() 기능 사용 => 자동으로 EncrypterConfig Bean과 연결됨
        User saveUser = userRepository.save(request.toEntity(encoder.encode(request.getLoginPw())));    // UserJoinRequest -> User Entity변환후 데이터 DB 저장 , password는 암호화 하여 저장

//        return UserDto.fromEntity(saveUser2);    // User에게 입력받아 회원가입한 데이터를 UserDto에 저장함
        return "회원가입 성공";
    }

    public IdCheckRes checkId(String loginId) {

        boolean userExists = userRepository.findByLoginId(loginId).isPresent();

        String message = userExists ? "FAIL" : "SUCCESS";

        return IdCheckRes.builder().message(message).build();
    }

    public BillAddRes addBills(String userId, BillAddReq billAddReq) {
        User user = userRepository.findByUserId(Long.valueOf(userId)).orElseThrow(() -> new UserNotExistException());

        Bill saveBill = billRepository.save(billAddReq.toEntity(user));

        // 메뉴 리스트
        List<String> menuList = billAddReq.getMenuList();

        // 메뉴 저장
        for (String menu : menuList){
            BillMenu billMenu = BillMenu.builder().menuName(menu).bill(saveBill).build();
            billMenuRepository.save(billMenu);
        }

        userRepository.save(user);

        return new BillAddRes().toRes(billAddReq, Long.valueOf(userId));
    }

    public List<BillRes> getBills(String userId) {
        User user = userRepository.findByUserId(Long.valueOf(userId)).orElseThrow(() -> new UserNotExistException());

        List<Bill> bills = user.getBills();
        List<BillRes> billResList = new ArrayList<>();

        for (Bill bill : bills) {
            List<String> menuList = new ArrayList<>();
            List<BillMenu> billMenus = bill.getBillMenus();
            for (BillMenu billMenu : billMenus){
                menuList.add(billMenu.getMenuName());
            }
            String changeDate = bill.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            BillRes billRes = BillRes.builder()
//                    .billId(bill.getBillId())
                    .storeName(bill.getStoreName())
                    .paymentMethod(String.valueOf(bill.getPaymentMethod()))
                    .totalPrice(bill.getTotalPrice())
                    .requests(bill.getRequests())
                    .createdDate(changeDate)
                    .menuList(menuList)
                    .build();

            billResList.add(billRes);
        }
        return billResList;
    }
    public int joinPay (String userId, boolean status) {
        User user = userRepository.findByUserId(Long.valueOf(userId)).orElseThrow(() -> new UserNotExistException());

        user.changePay(status);

        userRepository.save(user);

        return 200;
    }

    // 사장 store 컬럼 변경
    public void addStore (String userId, Long storeId) {
        User user = userRepository.findByUserId(Long.valueOf(userId)).orElseThrow(() -> new UserNotExistException());

        user.changeStoreId(storeId);

        userRepository.save(user);

    }
    
}
