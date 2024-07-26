package com.mine.springai.service.impl;

import com.mine.springai.service.GroqAIService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}