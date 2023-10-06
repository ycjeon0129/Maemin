package com.tft.paymentservice.api.payment.service;

import com.tft.paymentservice.api.payment.db.entity.Payment;
import com.tft.paymentservice.api.payment.db.repository.PaymentRepository;
import com.tft.paymentservice.api.payment.dto.Method;
import com.tft.paymentservice.api.payment.dto.Status;
import com.tft.paymentservice.api.payment.dto.request.PaymentKakaoReq;
import com.tft.paymentservice.api.payment.dto.request.PaymentReq;
import com.tft.paymentservice.api.payment.dto.request.RefundReq;
import com.tft.paymentservice.api.payment.dto.response.PaymentLogRes;
import com.tft.paymentservice.api.payment.dto.response.PaymentRes;
import com.tft.paymentservice.common.dto.*;
import com.tft.paymentservice.common.feign.PayFeignClient;
import com.tft.paymentservice.common.util.RequestUtil;
import com.tft.paymentservice.common.util.TimeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.tft.paymentservice.common.util.LogCurrent.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PayFeignClient payFeignClient;
    private final PaymentRepository paymentRepository;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private final String AUTHENTICATION_CODE = "authentication_code::";
    private final String PAYMENT_CODE = "payment_code::";
    private final String MM = "매장의민족";
    private final String KAKAO = "kakao::";
    @Value("${custom.url}"+"/payment-service/payment")
    private String REDIRECT_URL;
    @Value("${custom.key.kakao.admin}")
    private String KAKAO_ADMIN_KEY;

    @Transactional
    public PaymentRes createPayment(PaymentReq paymentReq) throws IllegalAccessException {
        log.info(logCurrent(getClassName(), getMethodName(), START));

        Long userId = RequestUtil.getUserId();

        Payment payment = Payment.builder()
                .payMethod(Method.values()[0])
                .paymentRequestId(paymentReq.getRequestId())
                .amount(paymentReq.getAmount())
                .build();

        PaymentRes paymentRes = PaymentRes.builder()    // 디폴트 값: 유효하지 않은 결제 수단인 경우
                .method(Method.getMethod(0))
                .requestId(paymentReq.getRequestId())
                .amount(paymentReq.getAmount())
                .status(Status.PAYMENT_FAIL.getKrName())
                .build();

        PayPaymentReq payReq = PayPaymentReq.builder()
                .payId(paymentReq.getPayId())
                .requestId(paymentReq.getRequestId())
                .amount(paymentReq.getAmount())
                .code(paymentReq.getCode())
                .build();
        // Redis에서 authentication code 확인
        String savedUserId = (String) redisTemplate.opsForValue().get(AUTHENTICATION_CODE+paymentReq.getCode());
        if (savedUserId.isEmpty()) {  // code 없음
            throw new NullPointerException();
        }
        if (!savedUserId.equals(userId.toString())) {
            throw new IllegalAccessException();
        }
//            AuthenticationCode code = authenticationCodeRepository.findById(paymentReq.getCode())
//                    .orElseThrow(NullPointerException::new);
//            if (!code.getUserId().equals(userId)) {
//                throw new IllegalAccessException();
//            }
        PayPaymentRes payRes = payFeignClient.payPayment(payReq);

        payment.updatePaymentDate(payRes.getPayedDate());
        payment.updateStatus(Status.PAYMENT_COMPLETE);
        paymentRepository.save(payment);

        String uuid = userId + TimeUtil.getTimeUuid();
        redisTemplate.opsForValue().set(PAYMENT_CODE + uuid, userId.toString());

        paymentRes.setPaymentDate(payRes.getPayedDate());
        paymentRes.setStatus(Status.PAYMENT_COMPLETE.getKrName());
        paymentRes.setAuthCode(uuid);

//            // pay confirm
//            PayConfirmReq payConfirmReq = PayConfirmReq.builder()
//                    .requestId(paymentReq.getRequestId())
//                    .amount(paymentReq.getAmount())
//                    .build();
//            PayConfirmRes confirmRes = payFeignClient.confirmPayment(payConfirmReq);
//
//            // pay approve
//            if (paymentReq.getRequestId().equals(confirmRes.getRequestId()) && paymentReq.getAmount() == confirmRes.getAmount()) {
//                PayApproveReq payApproveReq = PayApproveReq.builder()
//                        .requestId(confirmRes.getRequestId())
//                        .paymentKey(confirmRes.getPaymentKey())
//                        .amount(confirmRes.getAmount())
//                        .build();
//                PayApproveRes approveRes = payFeignClient.approvePayment(payApproveReq);
//
//                paymentRes = PaymentRes.builder()
//                        .paymentDate(approveRes.getPayedDate())
//                        .status(Status.PAYMENT_COMPLETE.getKrName())
//                        .build();
//            } else {
//                log.info(logCurrent(getClassName(), getMethodName(), END));
//                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong Request Information");
//            }
        log.info(logCurrent(getClassName(), getMethodName(), END));
        return paymentRes;
    }

    @Transactional
    public KakaoReadyRes createKakaoPayment(PaymentKakaoReq paymentKakaoReq) throws IllegalAccessException {
        log.info(logCurrent(getClassName(), getMethodName(), START));

//        Long userId = RequestUtil.getUserId();

//        Payment payment = Payment.builder()
//                .payMethod(Method.values()[1])
//                .paymentRequestId(paymentKakaoReq.getRequestId())
//                .amount(paymentKakaoReq.getAmount())
//                .build();

//        PaymentRes paymentRes = PaymentRes.builder()    // 디폴트 값: 유효하지 않은 결제 수단인 경우
//                .method(Method.getMethod(1))
//                .requestId(paymentKakaoReq.getRequestId())
//                .amount(paymentKakaoReq.getAmount())
//                .status(Status.PAYMENT_FAIL.getKrName())
//                .build();
        Long userId = RequestUtil.getUserId();

        String uuid = TimeUtil.getTimeUuid();

        // 카카오가 요구한 결제요청request값을 담아줍니다.
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.add("cid", "TC0ONETIME");
        parameters.add("partner_order_id", MM+"-"+paymentKakaoReq.getStoreId()
                +"-"+paymentKakaoReq.getTableId()+"-"+paymentKakaoReq.getSessionId());
        parameters.add("partner_user_id", MM);
        parameters.add("item_name", MM+"-"+paymentKakaoReq.getStore()+"-"+uuid);
        parameters.add("quantity", String.valueOf(1));
        parameters.add("total_amount", String.valueOf(paymentKakaoReq.getAmount()));
        parameters.add("tax_free_amount", String.valueOf(0));
        parameters.add("approval_url", REDIRECT_URL+
                "/kakao/success/"+paymentKakaoReq.getStoreId()+"/"+paymentKakaoReq.getTableId()+"/"+paymentKakaoReq.getSessionId()+"/"+userId); // 결제승인시 넘어갈 url
        parameters.add("cancel_url", REDIRECT_URL+"/kakao/cancel"); // 결제취소시 넘어갈 url
        parameters.add("fail_url", REDIRECT_URL+"/kakao/fail"); // 결제 실패시 넘어갈 url

        log.info("파트너주문아이디:"+ parameters.get("partner_order_id")) ;
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, RequestUtil.getHeaders(KAKAO_ADMIN_KEY));
        // 외부url요청 통로 열기.
        RestTemplate template = new RestTemplate();
        String url = "https://kapi.kakao.com/v1/payment/ready";
        // template으로 값을 보내고 받아온 ReadyResponse값 readyResponse에 저장.
        KakaoReadyRes readyRes = template.postForObject(url, requestEntity, KakaoReadyRes.class);
        log.info("결재준비 응답객체: " + readyRes);

        redisTemplate.opsForValue().set(KAKAO+"tid-"
                +userId+"-"+paymentKakaoReq.getSessionId(), readyRes.getTid());
        redisTemplate.opsForValue().set(KAKAO+"req-"
                +userId+"-"+paymentKakaoReq.getSessionId(), paymentKakaoReq.getRequestId().toString());

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return readyRes;
    }

    @Transactional
    public String approveKakaoPayment(String pgToken, Long storeId, Long tableId, Long sessionId, Long userId) {
        log.info(logCurrent(getClassName(), getMethodName(), START));


        /** 요청 Body */
        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        // 가맹점 코드 테스트코드는 TC0ONETIME 이다.
        map.add("cid", "TC0ONETIME");
        String tid = (String) redisTemplate.opsForValue().get(KAKAO+"tid-"
                +userId+"-"+sessionId);
        String requestId = (String) redisTemplate.opsForValue().get(KAKAO+"req-"
                +userId+"-"+sessionId);

        // getReadyRequest 에서 받아온 tid
        map.add("tid", tid);
        map.add("partner_order_id", MM+"-"+storeId+"-"+tableId+"-"+sessionId); // 주문명
        map.add("partner_user_id", MM);

        // getReadyRequest에서 받아온 redirect url에 클라이언트가
        // 접속하여 결제를 성공시키면 아래의 url로 redirect 되는데
        // http://localhost:8080/payment/success"+"/"+id
        // 여기에 &pg_token= 토큰값 이 붙어서 redirect 된다.
        // 해당 내용을 뽑아 내서 사용하면 된다.
        map.add("pg_token", pgToken);


        /** Header와 Body 합쳐서 RestTemplate로 보내기 위한 밑작업 */
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(map, RequestUtil.getHeaders(KAKAO_ADMIN_KEY));
        String url = "https://kapi.kakao.com/v1/payment/approve";
        // 요청 보내기
        RestTemplate rt = new RestTemplate();
        KakaoApproveRes approveRes = rt.postForObject(url, requestEntity, KakaoApproveRes.class);

        Payment payment = Payment.builder()
                .payMethod(Method.values()[1])
                .paymentRequestId(Long.parseLong(requestId))
                .amount(approveRes.getAmount().getTotal())
                .build();

        String uuid = userId + TimeUtil.getTimeUuid();
        redisTemplate.opsForValue().set(PAYMENT_CODE + uuid, userId.toString(), 300, TimeUnit.SECONDS);

        payment.updatePaymentDate(approveRes.getApproved_at().replace('T', ' '));
        payment.updateStatus(Status.PAYMENT_COMPLETE);

        paymentRepository.save(payment);

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return uuid;
    }

    public List<PaymentLogRes> getPaymentLog(int page, int count) {
        log.info(logCurrent(getClassName(), getMethodName(), START));

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return null;
    }

    public List<PaymentRes> updatePayment(Long orderId, RefundReq refundReq) {
        log.info(logCurrent(getClassName(), getMethodName(), START));

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return null;
    }

}
