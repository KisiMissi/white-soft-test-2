package org.kaoden.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.kaoden.objects.OriginalMessage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class JsonReader {
    // Read data.json from classpath
    public static String[] readUserMessages(String dataFilePath) {
        ObjectMapper mapper = new ObjectMapper();
        File dataFile = new File(dataFilePath);

        fileExistenceCheck(dataFile);

        String[] userMessages;
        try {
            userMessages = mapper.readValue(dataFile, String[].class);
        } catch (IOException ex) {
            throw new RuntimeException("An error occurred while reading file with user messages.");
        }
        return userMessages;
    }

    public static String[] readUserMessagesFromAPI(String dataFileURL) {
        ObjectMapper mapper = new ObjectMapper();
        String[] userMessages;
        try {
            userMessages = mapper.readValue(new URL(dataFileURL), String[].class);
        } catch (MalformedURLException ex) {
            throw new IllegalArgumentException("An error occurred while forming the URL.");
        } catch (IOException ex) {
            throw new RuntimeException("An error occurred while reading user messages.");
        }
        return userMessages;
    }

    public static List<OriginalMessage> readOriginalMessages(String replacementFilePath) {
        ObjectMapper mapper = new ObjectMapper();
        File replacementFile = new File(replacementFilePath);

        fileExistenceCheck(replacementFile);

        List<OriginalMessage> originalMessages = null;
        try {
            originalMessages = List.of(mapper.readValue(replacementFile, OriginalMessage[].class));
        } catch (IOException ex) {
            throw new RuntimeException("An error occurred while reading file with replaced messages.");
        }
        return originalMessages;
    }

    private static void fileExistenceCheck(File file) {
        if (! file.exists())
            throw new RuntimeException("File: \"" + file.toPath() + "\" doesn't exists.");
    }
}
