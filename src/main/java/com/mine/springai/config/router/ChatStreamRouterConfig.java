package com.mine.springai.config.router;

import com.mine.springai.config.handler.ChatStreamHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class ChatStreamRouterConfig {
    @Bean
    public RouterFunction<ServerResponse> chatStream(ChatStreamHandler chatStreamHandler) {
        return RouterFunctions
                .route(GET("/stream/chat/{query}")
                        .and(accept(MediaType.APPLICATION_JSON)), chatStreamHandler::getResponseStream);
    }
}
