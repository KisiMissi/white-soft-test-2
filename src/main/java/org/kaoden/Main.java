package org.kaoden;

import org.kaoden.io.JsonWriter;

import java.util.List;

public class Main {
    public static void main( String[] args ) {
        MessageProofreader proofreader = new MessageProofreader();
        List<String> resultMessages = proofreader.getCorrectedMessages();

        JsonWriter.writeMessages(resultMessages);
    }
}
