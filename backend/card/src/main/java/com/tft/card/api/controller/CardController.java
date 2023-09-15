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

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/card")
public class CardController {

    private final CardService cardService;

    @PostMapping
    public ResponseEntity<CardRegistRes> createCardPayment(@RequestBody CardRegistReq cardRegistReq) {
        CardRegistRes card = cardService.createCardPayment(cardRegistReq);

//        throw new PaymentNotExistException();
        return ResponseEntity.status(200).body(card);
    }

    @PostMapping("/payment")
    public ResponseEntity<?> createPayment(@RequestBody CardPaymentReq cardPaymentReq) {
        cardService.createPayment(cardPaymentReq);

        return ResponseEntity.status(200).body(null);
    }

}
