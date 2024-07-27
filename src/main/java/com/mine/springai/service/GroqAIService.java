package com.mine.springai.service;

import org.springframework.ai.chat.prompt.Prompt;
import reactor.core.publisher.Flux;

public interface GroqAIService {
    String getResponse(String query);

    Flux<String> getResponseStream(Prompt prompt);
}
