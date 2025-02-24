package com.TinkerersLab.LabAssistant.service;

import org.springframework.stereotype.Service;

import com.TinkerersLab.LabAssistant.model.ChatRequest;
import com.TinkerersLab.LabAssistant.model.llm.RagAiAssistant;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class RagAiService {

    public final RagAiAssistant assistant;

    public String chat(ChatRequest chatRequest) {
        return assistant.chat(chatRequest.memoryId(), chatRequest.question());
    }
}
