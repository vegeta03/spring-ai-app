package com.mine.springai.service.impl;

import com.mine.springai.service.GroqAIService;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

@Service
public class GroqAIServiceImpl implements GroqAIService {

    private final OpenAiChatModel chatModel;

    public GroqAIServiceImpl(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public String getResponse(String query) {
        ChatResponse response = chatModel.call(new Prompt(query));
        return response.getResult().getOutput().getContent();
    }
}
