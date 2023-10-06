package com.tft.cartservice.sse.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RequiredArgsConstructor
@Component
public class SseEmitters {

    // Map을 사용하여 storeId와 SSE 연결을 매핑합니다.
    private final Map<String, SseEmitter> storeEmitters = new ConcurrentHashMap<>();

    //    @Transactional
    public SseEmitter add(String storeId, SseEmitter emitter) {
        // 가게별로 SSE 연결 리스트를 가져옵니다.
//        List<SseEmitter> emitters = storeEmitters.computeIfAbsent(storeId, key -> new CopyOnWriteArrayList<>());
//
//        // SSE 연결을 리스트에 추가합니다.
//        emitters.add(emitter);
        storeEmitters.put(storeId, emitter);

        log.info("new emitter added for store {}: {}", storeId, emitter);

        emitter.onCompletion(() -> {
            // SSE 연결이 완료될 때 해당 연결을 리스트에서 삭제합니다.
            storeEmitters.remove(storeId);
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
        SseEmitter emitter = storeEmitters.get(storeId);
        log.info("send 준비 : {}", emitter);
        if (emitter != null) {
            try {
                log.info(String.valueOf(emitter));
                emitter.send(SseEmitter.event()
                        .name("order")
                        .data(message));
            } catch (IOException e) {
                if (e.getMessage().contains("Broken pipe")) {
                    log.warn("SSE 연결이 끊어짐. 무시됨.", e); // 경고 메시지로 로그를 기록
                } else {
                    log.error("Error sending order alert to store {}: {}", storeId, e.getMessage());
                    storeEmitters.remove(storeId);
                }
            }
        }
    }
}