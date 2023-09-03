package org.kaoden.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.kaoden.objects.OriginalMessage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class JsonReader {

    private static final String DATA_FILE_PATH = "src/main/resources/data.json";
    public static final String DATA_URL =
            "https://raw.githubusercontent.com/thewhitesoft/student-2023-assignment/main/data.json";
    public static final String REPLACEMENT_FILE_PATH = "src/main/resources/replacement.json";

    // Read data.json from classpath
    public static String[] readUserMessages() {
        ObjectMapper mapper = new ObjectMapper();
        File dataFile = new File(DATA_FILE_PATH);

        fileExistenceCheck(dataFile);

        String[] userMessages = null;
        try {
            userMessages = mapper.readValue(dataFile, String[].class);
        } catch (IOException ex) {
            System.out.println("An error occurred while reading file with user messages.\n" + ex);
            System.exit(-1);
        }
        return userMessages;
    }

    public static String[] readUserMessagesFromAPI() {
        ObjectMapper mapper = new ObjectMapper();
        String[] userMessages = null;
        try {
            userMessages = mapper.readValue(new URL(DATA_URL), String[].class);
        } catch (MalformedURLException ex) {
            System.out.println("An error occurred while forming the URL.\n" + ex);
            System.exit(-1);
        } catch (IOException ex) {
            System.out.println("An error occurred while reading user messages.\n" + ex);
            System.exit(-1);
        }
        return userMessages;
    }

    public static List<OriginalMessage> readOriginalMessages() {
        ObjectMapper mapper = new ObjectMapper();
        File replacementFile = new File(REPLACEMENT_FILE_PATH);

        fileExistenceCheck(replacementFile);

        List<OriginalMessage> originalMessages = null;
        try {
            originalMessages = List.of(mapper.readValue(replacementFile, OriginalMessage[].class));
        } catch (IOException ex) {
            System.out.println("An error occurred while reading file with replaced messages.\n" + ex);
            System.exit(-1);
        }
        return originalMessages;
    }

    private static void fileExistenceCheck(File file) {
        if (! file.exists()) {
            System.out.println("File: \"" + file.toPath() + "\" doesn't exists.");
            System.exit(-1);
        }
    }
}
