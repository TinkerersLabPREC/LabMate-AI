package com.TinkerersLab.LabAssistant.controller;

import org.springframework.web.bind.annotation.RestController;

import com.TinkerersLab.LabAssistant.model.LLMRequest;
import com.TinkerersLab.LabAssistant.model.LLMResponse;
import com.TinkerersLab.LabAssistant.service.LLMService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@Slf4j
@RequestMapping("/api/v1/llm")
@AllArgsConstructor
public class LLMController {

    private final LLMService llmService;

    @GetMapping("/ask")
    public Flux<String> ask(@RequestParam String message) {
        // return ResponseEntity.ok(new LLMResponse("success",
        // llmService.ask(request)));

        return llmService.askAndStream(message);
    }

    // @GetMapping("/ask")
    // public String getMethodName(@RequestParam String param) {
    // return new String();
    // }

}
