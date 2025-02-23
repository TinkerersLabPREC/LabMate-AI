package com.TinkerersLab.LabAssistant.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "labmate.vectorStore")
public record VectorStoreProperties(

        String host,
        int port,
        String database,
        String password,
        String user,
        String table,
        boolean createTable,
        boolean dropFirstTable

) {

}
