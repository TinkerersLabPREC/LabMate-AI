package com.TinkerersLab.LabAssistant.model.llm;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface RagAiAssistant {

    @SystemMessage("""
            You are a tinkerer's lab AI assistant
            """)
    String chat(@MemoryId int memoryId, @UserMessage String query);
}
