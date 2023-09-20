package com.tft.card.api.service;

import com.tft.card.api.db.entity.Card;
import com.tft.card.api.db.repository.CardRepository;
import com.tft.card.api.dto.request.CardPaymentReq;
import com.tft.card.api.dto.request.CardRegistReq;
import com.tft.card.api.dto.response.CardRegistRes;
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

    @Transactional
    public CardRegistRes createCardPayment(CardRegistReq cardRegistReq) {
        log.info(logCurrent(getClassName(), getMethodName(), START));

        String uuid = UUID.randomUUID().toString();

        Card card = Card.builder()
                .cardNumber(cardRegistReq.getCardNumber())
                .cardExpireDate(cardRegistReq.getCardExpireDate())
                .cvc(cardRegistReq.getCvc())
                .billingKey(uuid)
                .build();

        cardRepository.save(card);

        CardRegistRes cardRegist = CardRegistRes.builder()
                .billingKey(uuid)
                .build();

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return cardRegist;
    }

    public void createPayment(CardPaymentReq cardPaymentReq) {
    }
}
