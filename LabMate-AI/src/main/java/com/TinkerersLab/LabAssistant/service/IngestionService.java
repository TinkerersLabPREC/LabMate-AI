package com.TinkerersLab.LabAssistant.service;

import java.io.File;
import java.net.MalformedURLException;

import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.TinkerersLab.LabAssistant.config.ApplicationConstants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IngestionService {

    private final VectorStore vectorStore;

    public IngestionService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public void ingestAll() {
        File dir = new File(ApplicationConstants.DEFAULT_RESOURCE_PATH);
        File[] files = dir.listFiles();
        System.out.println("files : " + files.length);

        for (File file : files) {
            log.info("embedding text to vector " + file.getName());
            Resource resource;
            try {
                resource = new FileUrlResource(file.getAbsolutePath());
            } catch (MalformedURLException e) {
                e.printStackTrace();
                continue;
            }

            var pdfReader = new PagePdfDocumentReader(resource);
            TextSplitter textSplitter = new TokenTextSplitter();
            vectorStore.accept(textSplitter.apply(pdfReader.get()));
            log.info("Vector store loaded with " + file.getName());
        }
    }
}
