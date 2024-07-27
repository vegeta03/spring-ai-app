package com.mine.springai.service.impl;

import com.mine.springai.service.GroqAIService;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@SpringBootTest
class GroqAIServiceImplTest {

    @Autowired
    GroqAIService groqAIService;


    @Test
    void getResponse() {
        Prompt prompt = new Prompt(new UserMessage("Write a python function to output numbers from 1 tp 100."));
        Mono<String> answerMono = groqAIService.getResponse(prompt);
        System.out.println("############################Got the answer.############################");
        answerMono
                .doOnNext(System.out::println)
                .block();
        System.out.println("\n#####################################################################");
    }

    @Test
    void getResponseStream() {
        Prompt prompt = new Prompt(new UserMessage("Write a python function to output numbers from 1 tp 100."));
        Flux<String> responseStream = groqAIService.getResponseStream(prompt);
        System.out.println("############################Got the answer.############################");
        responseStream
                .delayElements(Duration.ofMillis(50))
                .doOnNext(System.out::print)
                .doOnComplete(() -> System.out.println("\n#######################################################################"))
                .blockLast();
    }
}