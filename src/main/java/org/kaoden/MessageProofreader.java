package org.kaoden;

import org.kaoden.io.JsonReader;
import org.kaoden.objects.OriginalMessage;

import java.util.*;

public class MessageProofreader {

    public List<String> getCorrectedMessages() {
        List<OriginalMessage> originalMessagesList =
                deleteDuplicatedOriginalMessages(JsonReader.readOriginalMessages());
        return retrieveModifiedMessages(JsonReader.readUserMessagesFromAPI(), originalMessagesList);
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
            System.out.println("User messages is empty.");
            System.exit(1);
        } else if (originalMessages == null) {
            System.out.println("Original messages is empty.");
            System.exit(1);
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
