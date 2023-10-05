//package com.tft.cartservice.sse.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
//
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//@RequiredArgsConstructor
//@Controller
//public class NotificationController {
//
//    private final NotificationService notificationService;
//
//    public static Map<Long, SseEmitter> sseEmitters = new ConcurrentHashMap<>();
//
//    @GetMapping(value = "/sub", produces = "text/event-stream")
//    public SseEmitter subscribe(@AuthenticationPrincipal PrincipalDetails principalDetails,
//                                @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId) {
//
//        Long userId = principalDetails.getUser().getId();
//
//        SseEmitter sseEmitter = notificationService.subscribe(userId);
//
//        return sseEmitter;
//    }
//}
