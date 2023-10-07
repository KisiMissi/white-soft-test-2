package org.kaoden.io;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonWriter {

    public static final String RESULT_FILE = "src/main/resources/result.json";

    public static void writeMessages(List<String> messages) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(RESULT_FILE), messages);
        } catch (IOException ex) {
            throw new RuntimeException("An error occurred while writing result messages to the file.");
        }
    }
}
