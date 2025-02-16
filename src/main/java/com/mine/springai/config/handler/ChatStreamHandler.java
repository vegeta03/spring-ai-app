package com.mine.springai.config.handler;

import com.mine.springai.service.GroqAIService;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ChatStreamHandler {

    private final GroqAIService groqAIService;

    @Autowired
    public ChatStreamHandler(GroqAIService groqAIService) {
        this.groqAIService = groqAIService;
    }

    public Mono<ServerResponse> getResponseStream(ServerRequest serverRequest) {
        String query = serverRequest.pathVariable("query");
        Prompt prompt = new Prompt(new UserMessage(query));

        Mono<ServerResponse> noResponseMono = ServerResponse.notFound().build();

        Flux<String> responseContentFlux = this.groqAIService.getResponseStream(prompt);

        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(responseContentFlux, String.class)
                .switchIfEmpty(noResponseMono);
    }
}
