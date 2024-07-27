package com.mine.springai.service.impl;

import com.mine.springai.service.GroqAIService;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

@SpringBootTest
class GroqAIServiceImplTest {

    @Autowired
    GroqAIService groqAIService;


    @Test
    void getResponse() {
        String answer = groqAIService.getResponse("Write a python function to output numbers from 1 tp 100.");
        System.out.println("############################Got the answer.############################");
        System.out.println(answer);
        System.out.println("#######################################################################");
    }

    @Test
    void getResponseStream() {
        Prompt prompt = new Prompt(new UserMessage("Write a python function to output numbers from 1 tp 100."));
        Flux<String> responseStream = groqAIService.getResponseStream(prompt);
        System.out.println("############################Got the answer.############################");
        responseStream
                .doOnNext(System.out::print)
                .doOnComplete(() -> System.out.println("\n#######################################################################"))
                .blockLast();
    }
}