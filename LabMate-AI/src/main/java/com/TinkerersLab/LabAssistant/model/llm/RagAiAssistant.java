package com.TinkerersLab.LabAssistant.model.llm;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface RagAiAssistant {

    @SystemMessage("""
            You are a tinkerer's lab AI assistant, Speak to the user politely and warmly,
            If the user asks you something, respond to the used with information from vector database
            """)
    String chat(@MemoryId int memoryId, @UserMessage String query);
}
