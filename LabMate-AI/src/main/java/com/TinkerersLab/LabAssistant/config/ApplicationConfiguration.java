package com.TinkerersLab.LabAssistant.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.TinkerersLab.LabAssistant.service.IngestionService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@AllArgsConstructor
@Slf4j
public class ApplicationConfiguration {

    private final IngestionService ingestionService;

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            ingestionService.ingestAll();
            log.info("All files ingested from ", ApplicationConstants.DEFAULT_RESOURCE_PATH);
        };
    }

}
