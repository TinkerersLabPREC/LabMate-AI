package com.TinkerersLab.LabAssistant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class LabAssistantApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabAssistantApplication.class, args);
	}

}
