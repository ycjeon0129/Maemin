package com.tft.card.api.controller;

import com.tft.card.api.dto.request.CardPaymentReq;
import com.tft.card.api.dto.request.CardRegistReq;
import com.tft.card.api.dto.response.CardRegistRes;
import com.tft.card.api.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tft.card.common.util.LogCurrent.*;
import static com.tft.card.common.util.LogCurrent.START;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/card")
public class CardController {

    private final CardService cardService;

    @PostMapping
    public ResponseEntity<CardRegistRes> createCard(@RequestBody CardRegistReq cardRegistReq) {
        log.info(logCurrent(getClassName(), getMethodName(), START));
        CardRegistRes card = cardService.createCard(cardRegistReq);

//        throw new PaymentNotExistException();
        log.info(logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(200).body(card);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCard(@RequestParam String billingKey) {
        log.info(logCurrent(getClassName(), getMethodName(), START));
        cardService.deleteCard(billingKey);

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(200).body(null);
    }

    @PostMapping("/payment")
    public ResponseEntity<?> createPayment(@RequestBody CardPaymentReq cardPaymentReq) {
        log.info(logCurrent(getClassName(), getMethodName(), START));
        cardService.createPayment(cardPaymentReq);

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(200).body(null);
    }

}
