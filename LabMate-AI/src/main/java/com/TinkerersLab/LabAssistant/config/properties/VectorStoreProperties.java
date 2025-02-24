package com.TinkerersLab.LabAssistant.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "labmate.vector-store")
public class VectorStoreProperties {

    private String host;
    private int port;
    private String database;
    private String password;
    private String user;
    private String table;
    private boolean createTable;
    private int indexListSize;
    private boolean dropFirstTable;

}
