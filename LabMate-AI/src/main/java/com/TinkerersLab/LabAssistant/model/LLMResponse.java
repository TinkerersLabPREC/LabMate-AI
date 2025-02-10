package com.TinkerersLab.LabAssistant.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LLMResponse {

    private String status;

    private String response;

}
