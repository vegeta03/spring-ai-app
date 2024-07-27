package com.mine.springai.service.impl;

import com.mine.springai.service.GroqAIService;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GroqAIServiceImpl implements GroqAIService {

    private final OpenAiChatModel chatModel;

    public GroqAIServiceImpl(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public Mono<String> getResponse(Prompt prompt) {
        String response = chatModel
                .call(prompt)
                .getResult()
                .getOutput()
                .getContent();

        return Mono.just(response);
    }

    @Override
    public Flux<String> getResponseStream(Prompt prompt) {
        return chatModel
                .stream(prompt)
                .flatMap(chatResponse -> {
                    String content = chatResponse.getResult().getOutput().getContent();
                    return content != null ? Flux.just(content) : Flux.empty();
                });
    }
}
