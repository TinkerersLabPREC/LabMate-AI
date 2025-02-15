package com.TinkerersLab.LabAssistant.service;

import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.stereotype.Service;

import com.TinkerersLab.LabAssistant.model.LLMRequest;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Service
@Slf4j
public class LLMService {

    private final ChatClient chatClient;

    public LLMService(ChatClient.Builder builder, PgVectorStore vectorStore) {
        this.chatClient = builder
                .defaultAdvisors(new QuestionAnswerAdvisor(vectorStore))
                .build();
    }

    public String ask(LLMRequest request) {

        String context = """
                Act like a Lab Assistant model
                QUESTION : {query}
                """;

        PromptTemplate template = new PromptTemplate(context);

        Prompt prompt = template.create(Map.of("query", request.getQuery()));

        return chatClient
                .prompt(prompt)
                .call()
                .content();
    }

    public Flux<String> askAndStream(String query) {

        String context = """
                Act like a Lab Assistant model
                QUESTION : {query}
                """;

        PromptTemplate template = new PromptTemplate(context);

        Prompt prompt = template.create(Map.of("query", query));

        return chatClient
                .prompt(prompt)
                .stream()
                .content();
    }
}
