package com.TinkerersLab.LabAssistant.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "labmate.llm-provider")
public class LLMProviderProperties {

    public String baseUrl;
    public String chatModel;
    public double temperature;
    public boolean logRequests;
    public boolean logResponses;
    public String embeddingModel;

}
