package com.tft.storeservice.sse.Service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.tft.storeservice.order.dto.response.OrderRes;
import com.tft.storeservice.sse.db.repository.SseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SseService {
	private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60 * 1000000;
	private final SseRepository sseRepository;

	public SseEmitter subscribe(Long storeId){
		SseEmitter emitter = createEmitter(storeId);

		sendToClient(storeId, null);
		return emitter;
	}

	public void notify(Long storeId, OrderRes orderRes){
		sendToClient(storeId, orderRes);
	}

	private void sendToClient(Long id, OrderRes orderRes){
		SseEmitter emitter = sseRepository.get(id);
		if(emitter != null){
			try{
				emitter.send(SseEmitter.event().id(String.valueOf(id)).name("sse").data(orderRes));
			}catch (IOException exception){
				sseRepository.deleteById(id);
				emitter.completeWithError(exception);
			}
		}
	}

	public SseEmitter createEmitter(Long id){
		SseEmitter emitter = new SseEmitter(DEFAULT_TIMEOUT);
		sseRepository.save(id, emitter);

		// emitter.onCompletion(()->sseRepository.deleteById(id));
		// emitter.onTimeout(()-> sseRepository.deleteById(id));
		return emitter;
	}
}
