package com.tft.card.api.controller;

import com.tft.card.api.dto.request.CardApproveReq;
import com.tft.card.api.dto.request.CardConfirmReq;
import com.tft.card.api.dto.request.CardRegistReq;
import com.tft.card.api.dto.response.CardApproveRes;
import com.tft.card.api.dto.response.CardConfirmRes;
import com.tft.card.api.dto.response.CardRegistRes;
import com.tft.card.api.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/confirm")
    public ResponseEntity<CardConfirmRes> confirmPayment(@RequestBody CardConfirmReq cardConfirmReq) {
        log.info(logCurrent(getClassName(), getMethodName(), START));
        CardConfirmRes cardConfirm = cardService.confirmPayment(cardConfirmReq);

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(200).body(cardConfirm);
    }

    @PostMapping("/approve")
    public ResponseEntity<CardApproveRes> approvePayment(@RequestBody CardApproveReq cardApproveReq) {
        log.info(logCurrent(getClassName(), getMethodName(), START));
        CardApproveRes cardApprove = cardService.approvePayment(cardApproveReq);

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(200).body(cardApprove);
    }

}
