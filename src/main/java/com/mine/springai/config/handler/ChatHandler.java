package com.mine.springai.config.handler;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ChatHandler {

    private final OpenAiChatModel chatModel;

    @Autowired
    public ChatHandler(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public Mono<ServerResponse> getResponse(ServerRequest serverRequest) {
        String query = serverRequest.pathVariable("query");
        Prompt prompt = new Prompt(new UserMessage(query));

        Mono<ServerResponse> noResponseMono = ServerResponse.notFound().build();

        Mono<String> responseContentMono = Mono.just(chatModel
                .call(prompt)
                .getResult().getOutput().getContent());

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseContentMono, String.class)
                .switchIfEmpty(noResponseMono);
    }
}
