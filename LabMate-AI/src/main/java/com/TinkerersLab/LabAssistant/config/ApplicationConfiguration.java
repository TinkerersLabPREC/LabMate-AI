package com.TinkerersLab.LabAssistant.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.TinkerersLab.LabAssistant.model.llm.RagAiAssistant;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.ollama.OllamaEmbeddingModel;
import dev.langchain4j.rag.DefaultRetrievalAugmentor;
import dev.langchain4j.rag.RetrievalAugmentor;
import dev.langchain4j.rag.content.injector.DefaultContentInjector;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.pgvector.PgVectorEmbeddingStore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Configuration
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationConfiguration {

    LLMProviderProperties llmProviderProperties;

    VectorStoreProperties vectorStoreProperties;

    @Bean
    EmbeddingModel embeddingModel() {

        return OllamaEmbeddingModel.builder()
                .baseUrl(llmProviderProperties.baseUrl())
                .modelName(llmProviderProperties.embedding_model())
                .build();
    }

    @Bean
    EmbeddingStore<TextSegment> embeddingStore() {
        return PgVectorEmbeddingStore.builder()
                .host(vectorStoreProperties.host())
                .port(vectorStoreProperties.port())
                .user(vectorStoreProperties.user())
                .password(vectorStoreProperties.password())
                .database(vectorStoreProperties.database())
                .table(vectorStoreProperties.table())
                .createTable(vectorStoreProperties.createTable())
                .dimension(embeddingModel().dimension())
                .useIndex(true)
                .dropTableFirst(true)
                .build();

    }

    @Bean
    RagAiAssistant ragAiAssistant() {
        EmbeddingStoreContentRetriever contentRetriever = EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore())
                .embeddingModel(embeddingModel())
                .build();

        DefaultContentInjector contentInjector = DefaultContentInjector.builder()
                .metadataKeysToInclude(List.of("file_content_short_description", "file_name", "index"))
                .build();

        RetrievalAugmentor retrievalAugmentor = DefaultRetrievalAugmentor.builder()
                .contentRetriever(contentRetriever)
                .contentInjector(contentInjector)
                .build();

        return AiServices.builder(RagAiAssistant.class)
                .chatLanguageModel(chatLanguageModel())
                .retrievalAugmentor(retrievalAugmentor)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(10))
                // .streamingChatLanguageModel(chatLanguageModel())
                .build();
    }

    @Bean
    ChatLanguageModel chatLanguageModel() {
        return OllamaChatModel.builder()
                .baseUrl(llmProviderProperties.baseUrl())
                .modelName(llmProviderProperties.chat_model())
                .temperature(llmProviderProperties.temperature())
                .logRequests(llmProviderProperties.logRequests())
                .logResponses(llmProviderProperties.logResponses())
                .build();
    }
}
