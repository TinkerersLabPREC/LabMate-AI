package com.TinkerersLab.LabAssistant.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.TinkerersLab.LabAssistant.config.properties.LLMProviderProperties;
import com.TinkerersLab.LabAssistant.config.properties.VectorStoreProperties;
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
import dev.langchain4j.rag.query.transformer.ExpandingQueryTransformer;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.pgvector.PgVectorEmbeddingStore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationConfiguration {

    LLMProviderProperties llmProviderProperties;

    VectorStoreProperties vectorStoreProperties;

    @Bean
    EmbeddingModel embeddingModel() {

        return OllamaEmbeddingModel.builder()
                .baseUrl(llmProviderProperties.getBaseUrl())
                .modelName(llmProviderProperties.getEmbeddingModel())
                .build();
    }

    @Bean
    EmbeddingStore<TextSegment> embeddingStore() {
        return PgVectorEmbeddingStore.builder()
                .host(vectorStoreProperties.getHost())
                .port(vectorStoreProperties.getPort())
                .user(vectorStoreProperties.getUser())
                .password(vectorStoreProperties.getPassword())
                .database(vectorStoreProperties.getDatabase())
                .table(vectorStoreProperties.getTable())
                .createTable(vectorStoreProperties.isCreateTable())
                .dimension(embeddingModel().dimension())
                .useIndex(true)
                .indexListSize(vectorStoreProperties.getIndexListSize())
                .dropTableFirst(true)
                .build();

    }

    @Bean
    RagAiAssistant ragAiAssistant() {
        EmbeddingStoreContentRetriever contentRetriever = EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore())
                .embeddingModel(embeddingModel())
                .minScore(0.7)
                .maxResults(15)
                .build();

        DefaultContentInjector contentInjector = DefaultContentInjector.builder()
                .metadataKeysToInclude(List.of("file_name", "index"))
                .build();

        RetrievalAugmentor retrievalAugmentor = DefaultRetrievalAugmentor.builder()
                .contentRetriever(contentRetriever)
                .contentInjector(contentInjector)
                .contentAggregator(null)
                .queryTransformer(new ExpandingQueryTransformer(chatLanguageModel()))
                .build();

        return AiServices.builder(RagAiAssistant.class)
                .chatLanguageModel(chatLanguageModel())
                .retrievalAugmentor(retrievalAugmentor)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(10))
                .build();
    }

    @Bean
    ChatLanguageModel chatLanguageModel() {
        return OllamaChatModel.builder()
                .baseUrl(llmProviderProperties.getBaseUrl())
                .modelName(llmProviderProperties.getChatModel())
                .temperature(llmProviderProperties.getTemperature())
                .logRequests(llmProviderProperties.isLogRequests())
                .logResponses(llmProviderProperties.isLogResponses())
                .build();
    }
}
