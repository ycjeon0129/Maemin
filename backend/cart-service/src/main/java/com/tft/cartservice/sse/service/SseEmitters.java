package com.tft.cartservice.sse.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@RequiredArgsConstructor
@Component
public class SseEmitters {

    // Map을 사용하여 storeId와 SSE 연결을 매핑합니다.
    private final Map<String, List<SseEmitter>> storeEmitters = new ConcurrentHashMap<>();

//    @Transactional
    public SseEmitter add(String storeId, SseEmitter emitter) {
        // 가게별로 SSE 연결 리스트를 가져옵니다.
        List<SseEmitter> emitters = storeEmitters.computeIfAbsent(storeId, key -> new CopyOnWriteArrayList<>());

        // SSE 연결을 리스트에 추가합니다.
        emitters.add(emitter);

        log.info("new emitter added for store {}: {}", storeId, emitter);
        log.info("emitter list size for store {}: {}", storeId, emitters.size());

        emitter.onCompletion(() -> {
            // SSE 연결이 완료될 때 해당 연결을 리스트에서 삭제합니다.
            emitters.remove(emitter);
            log.info("emitter removed for store {}: {}", storeId, emitter);
        });

        emitter.onTimeout(() -> {
            // SSE 연결이 타임아웃될 때 연결을 종료합니다.
            emitter.complete();
            log.info("emitter timed out for store {}: {}", storeId, emitter);
        });

        return emitter;
    }

    public void sendOrderAlert(String storeId, String message) {
        // 특정 가게에 대한 주문 알람을 보냅니다.
        List<SseEmitter> emitters = storeEmitters.get(storeId);
        log.info("send 준비");
        log.info("보내기 전 emitters 길이 체크 : {}", String.valueOf(emitters.size()));
        if (emitters != null) {
            emitters.forEach(emitter -> {
                try {
                    log.info(String.valueOf(emitter));
                    emitter.send(SseEmitter.event()
                            .name("order")
                            .data(message));
                } catch (IOException e) {
                    log.error("Error sending order alert to store {}: {}", storeId, e.getMessage());
                }
            });
        }
    }
}