package com.TinkerersLab.LabAssistant.controller;

import org.springframework.web.bind.annotation.RestController;

import com.TinkerersLab.LabAssistant.model.ChatRequest;
import com.TinkerersLab.LabAssistant.service.RagAiService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@AllArgsConstructor
public class ChatController {

    private final RagAiService ragAiService;

    @GetMapping("/hello")
    public String getMethodName() {
        return new String("hello");
    }

    @PostMapping("/chat")
    public String chat(@RequestBody ChatRequest chatRequest) {
        System.out.println("received request");
        return new String(ragAiService.chat(chatRequest));
    }

}
