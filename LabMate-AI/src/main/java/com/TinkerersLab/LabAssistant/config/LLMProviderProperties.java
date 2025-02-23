package com.TinkerersLab.LabAssistant.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "labmate.llmProvider")
public record LLMProviderProperties(

        String baseUrl,
        String chat_model,
        double temperature,
        boolean logRequests,
        boolean logResponses,
        String embedding_model

) {

}
