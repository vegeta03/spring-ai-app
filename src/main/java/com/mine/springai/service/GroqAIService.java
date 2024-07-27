package com.mine.springai.service;

import org.springframework.ai.chat.prompt.Prompt;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GroqAIService {
    Mono<String> getResponse(Prompt prompt);

    Flux<String> getResponseStream(Prompt prompt);
}
