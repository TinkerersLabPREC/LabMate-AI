package com.TinkerersLab.LabAssistant.config;

import java.util.HashMap;
import java.util.List;

public class ApplicationConstants {

    public final static String[] DEFAULT_RESOURCE_PATH = new String[] { "/opt/docs/md" };

    public final static String DEFAULT_SYSTEM_MESSAGE_PATH = "/opt/docs/systemMsg";

    public final static String DEFAULT_CENSORED_WORDS_PATH = "/opt/docs/censoredWords";

    public static List<String> CENSORED_WORDS;

    public final static String DEFAULT_HASH_ALGORITHM = "SHA-256";

    public static HashMap<String, String> INGESTION_RECORD;

    public final static String DEFAULT_INGESTION_RECORD_PATH = "/home/happypotter/ingestion_record.dat";
}