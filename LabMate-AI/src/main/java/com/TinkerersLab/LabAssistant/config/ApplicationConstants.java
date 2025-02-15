package com.TinkerersLab.LabAssistant.config;

import java.util.HashMap;

public class ApplicationConstants {

    public final static String[] DEFAULT_RESOURCE_PATH = new String[] { "/opt/docs", "/opt/docs2" };

    public static final String DEFAULT_HASH_ALGORITHM = "SHA-256";

    public static HashMap<String, String> INGESTION_RECORD;

    public static final String DEFAULT_INGESTION_RECORD_PATH = "ingestion_record.dat";
}