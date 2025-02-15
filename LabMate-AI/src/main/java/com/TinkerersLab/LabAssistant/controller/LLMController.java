package com.TinkerersLab.LabAssistant.controller;

import org.springframework.web.bind.annotation.RestController;

import com.TinkerersLab.LabAssistant.model.LLMRequest;
import com.TinkerersLab.LabAssistant.model.LLMResponse;
import com.TinkerersLab.LabAssistant.service.LLMService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@Slf4j
@RequestMapping("/api/v1/llm")
@AllArgsConstructor
public class LLMController {

    private final LLMService llmService;

    @GetMapping("/ask")
    public ResponseEntity<LLMResponse> ask(@RequestBody LLMRequest request) {
        log.info("Received Get /ask with prompt :" + request.getQuery());
        return ResponseEntity.ok(new LLMResponse("success", llmService.ask(request)));
    }

    @GetMapping("/askAndStream")
    public Flux<String> askAndStream(@RequestParam String query) {
        log.info("Received Get /askAndStream with prompt :" + query);
        return llmService.askAndStream(query);
    }

}
