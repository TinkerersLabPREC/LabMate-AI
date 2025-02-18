package com.TinkerersLab.LabAssistant.config;

import java.util.HashMap;

public class ApplicationConstants {

    public final static String[] DEFAULT_RESOURCE_PATH = new String[] { "/opt/docs/pdf", "/opt/docs/text" };

    public static final String DEFAULT_HASH_ALGORITHM = "SHA-256";

    public static HashMap<String, String> INGESTION_RECORD;

    public static final String DEFAULT_INGESTION_RECORD_PATH = "/home/happypotter/ingestion_record.dat";
}