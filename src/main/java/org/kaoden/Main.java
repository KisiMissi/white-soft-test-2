package org.kaoden;

import org.kaoden.io.JsonWriter;

import java.util.List;

public class Main {

    private static final String DATA_FILE_PATH = "src/main/resources/data.json";
    public static final String DATA_URL =
            "https://raw.githubusercontent.com/thewhitesoft/student-2023-assignment/main/data.json";
    public static final String REPLACEMENT_FILE_PATH = "src/main/resources/replacement.json";

    public static void main( String[] args ) {
        MessageProofreader proofreader = new MessageProofreader();
        List<String> resultMessages = proofreader.getCorrectedMessages(DATA_URL, REPLACEMENT_FILE_PATH);
        JsonWriter.writeMessages(resultMessages);
    }
}
