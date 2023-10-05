package com.tft.card.api.service;

import com.tft.card.api.db.entity.Card;
import com.tft.card.api.db.entity.CardPayLog;
import com.tft.card.api.db.repository.CardPayLogRepository;
import com.tft.card.api.db.repository.CardRepository;
import com.tft.card.api.dto.request.CardApproveReq;
import com.tft.card.api.dto.request.CardConfirmReq;
import com.tft.card.api.dto.request.CardRegistReq;
import com.tft.card.api.dto.response.CardApproveRes;
import com.tft.card.api.dto.response.CardConfirmRes;
import com.tft.card.api.dto.response.CardRegistRes;
import com.tft.card.common.util.RandomUtil;
import com.tft.card.common.util.TimeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.tft.card.common.util.LogCurrent.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final CardPayLogRepository cardPayLogRepository;
    private final String MASK = "*-****-****-**";

    @Transactional
    public CardRegistRes createCard(CardRegistReq cardRegistReq) {
        log.info(logCurrent(getClassName(), getMethodName(), START));

        String uuid = UUID.randomUUID().toString();

        Card card = Card.builder()
                .cardNumber(cardRegistReq.getCardNumber())
                .cardExpireDate(cardRegistReq.getCardExpireDate())
                .cvc(cardRegistReq.getCvc())
                .billingKey(uuid)
                .build();

        cardRepository.save(card);

        StringBuilder basicInfo = new StringBuilder();
        basicInfo.append(cardRegistReq.getCardNumber().substring(0, 3))
                .append(MASK)
                .append(cardRegistReq.getCardNumber().substring(17));

        CardRegistRes cardRegist = CardRegistRes.builder()
                .basicInfo(basicInfo.toString())
                .billingKey(uuid)
                .build();

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return cardRegist;
    }

    public void deleteCard(String billingKey) {
        log.info(logCurrent(getClassName(), getMethodName(), START));

        Card card = cardRepository.findByBillingKey(billingKey)
                .orElseThrow(() -> new NullPointerException());
        cardRepository.delete(card);

        log.info(logCurrent(getClassName(), getMethodName(), END));
    }

    @Transactional
    public CardConfirmRes confirmPayment(CardConfirmReq cardConfirmReq) {
        log.info(logCurrent(getClassName(), getMethodName(), START));
        Card card = cardRepository.findByBillingKey(cardConfirmReq.getBillingKey())
                .orElseThrow( () -> new NullPointerException() );

        String paymentKey = RandomUtil.excuteGenerate();

        CardPayLog payLog = CardPayLog.builder()
                .card(card)
                .company(cardConfirmReq.getCompany())
                .paymentKey(paymentKey)
                .build();
        cardPayLogRepository.save(payLog);

        CardConfirmRes cardConfirmRes = CardConfirmRes.builder()
                .code(200)
                .paymentKey(paymentKey)
                .requestId(cardConfirmReq.getRequestId())
                .amount(cardConfirmReq.getAmount())
                .build();
        System.out.println(paymentKey);

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return cardConfirmRes;
    }

    @Transactional
    public CardApproveRes approvePayment(CardApproveReq cardApproveReq) {
        log.info(logCurrent(getClassName(), getMethodName(), START));
        System.out.println(cardApproveReq.getPaymentKey());
        Card card = cardRepository.findByBillingKey(cardApproveReq.getBillingKey())
                .orElseThrow( () -> new NullPointerException() );
        CardPayLog payLog = cardPayLogRepository.findByPaymentKey(cardApproveReq.getPaymentKey())
                .orElseThrow( () -> new NullPointerException() );
        payLog.approvePayment();

        CardApproveRes approveRes = CardApproveRes.builder()
                .code(200)
                .payedDate(TimeUtil.parseTimestamp(payLog.getPayedDate()))
                .build();

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return approveRes;
    }
}
