package org.kaoden;

import org.kaoden.io.JsonReader;
import org.kaoden.objects.OriginalMessage;

import java.util.*;

public class MessageProofreader {

    public List<String> getCorrectedMessages(String dataFileURL, String replacementFilePath) {
        List<OriginalMessage> originalMessagesList =
                deleteDuplicatedOriginalMessages(JsonReader.readOriginalMessages(replacementFilePath));
        return retrieveModifiedMessages(JsonReader.readUserMessagesFromAPI(dataFileURL), originalMessagesList);
    }

    public List<String> getCorrectedMessagesFromLocalFile(String dataFilePath, String replacementFilePath) {
        List<OriginalMessage> originalMessagesList =
                deleteDuplicatedOriginalMessages(JsonReader.readOriginalMessages(replacementFilePath));
        return retrieveModifiedMessages(JsonReader.readUserMessages(dataFilePath), originalMessagesList);
    }

    private List<OriginalMessage> deleteDuplicatedOriginalMessages(List<OriginalMessage> originalMessagesList) {
        Map<String, String> uniqueOriginalMessages = new HashMap<>();
        for (int i = originalMessagesList.size()-1; i>=0; i--) {
            if (! uniqueOriginalMessages.containsKey(originalMessagesList.get(i).getReplacement()))
                uniqueOriginalMessages.put(
                        originalMessagesList.get(i).getReplacement(),
                        originalMessagesList.get(i).getSource());
        }

        List<OriginalMessage> newOriginalMessagesList = new ArrayList<>();
        for (Map.Entry<String, String> entry : uniqueOriginalMessages.entrySet()) {
            OriginalMessage message = new OriginalMessage();
            message.setReplacement(entry.getKey());
            message.setSource(entry.getValue());
            newOriginalMessagesList.add(message);
        }
        return newOriginalMessagesList.stream()
                .sorted(Comparator.comparing(OriginalMessage::getReplacement).reversed())
                .toList();
    }

    private List<String> retrieveModifiedMessages(String[] userMessages, List<OriginalMessage> originalMessages) {
        if (userMessages == null) {
            throw new RuntimeException("No user messages.");
        } else if (originalMessages == null) {
            throw new RuntimeException("No original messages.");
        }

        List<String> resultMessages = new ArrayList<>();
        for (String message : userMessages) {
            String resultMessage = findAndReplaceReplacedPart(message, originalMessages);
            if (resultMessage != null)
                resultMessages.add(resultMessage);
        }
        return resultMessages;
    }

    private String findAndReplaceReplacedPart(String message, List<OriginalMessage> originalMessages) {
        for (OriginalMessage originalMessage : originalMessages) {
            if (message == null)
                continue;
            else if (! message.contains(originalMessage.getReplacement()))
                continue;

            if (originalMessage.getSource() != null)
                message = message.replaceAll(originalMessage.getReplacement(), originalMessage.getSource());
            else
                message = null;
        }
        return message;
    }
}
