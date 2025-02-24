package com.TinkerersLab.LabAssistant.config;

import java.util.HashMap;
import java.util.List;

public class ApplicationConstants {

    public static List<String> DEFAULT_RESOURCE_PATH = List.of("/opt/docs/md");

    public static HashMap<String, String> INGESTION_RECORD;

    public static String DEFAULT_INGESTION_RECORD_PATH = "/home/happypotter/ingestionRecord.sat";

    public final static String DEFAULT_CENSORED_WORDS_PATH = "/opt/docs/censoredWords";

    public static List<String> CENSORED_WORDS;

    public final static String DEFAULT_HASH_ALGORITHM = "SHA-256";
}
