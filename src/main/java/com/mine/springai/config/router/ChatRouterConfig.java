package com.mine.springai.config.router;

import com.mine.springai.config.handler.ChatHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class ChatRouterConfig {
    @Bean
    public RouterFunction<ServerResponse> chatRouter(ChatHandler chatHandler) {
        return RouterFunctions
                .route(GET("/chat/{query}"), chatHandler::getResponse);
    }
}
