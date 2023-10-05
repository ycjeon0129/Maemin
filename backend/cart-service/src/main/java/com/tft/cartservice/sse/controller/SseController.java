package com.tft.cartservice.sse.controller;

import com.tft.cartservice.sse.service.SseEmitters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SseController {

    private final SseEmitters sseEmitters;
    

    @GetMapping(value = "/connect/{storeId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> connect(@PathVariable String storeId) {
        SseEmitter emitter = new SseEmitter(60 * 100000L); // 만료시간 설정
        sseEmitters.add(storeId, emitter);

        try {
            emitter.send(SseEmitter.event()
                    .name("connect")
                    .data("connected!"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(emitter);
    }

    @PostMapping("/test/{storeId}/{message}")
    public ResponseEntity<Void> count(@PathVariable String storeId, @PathVariable String message) {
        sseEmitters.sendOrderAlert(storeId, message);
        return ResponseEntity.ok().build();
    }
}
