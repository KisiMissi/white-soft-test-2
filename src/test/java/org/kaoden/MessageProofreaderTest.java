package org.kaoden;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageProofreaderTest {

    @Test
    @DisplayName("Проверка где data.json пустой")
    void testWithEmptyDataFile() {
        // Arrange\
        String testDataPath = "src\\test\\resources\\proofreader\\dataEmpty.json";
        String testReplacementPath = "src\\test\\resources\\proofreader\\replacement.json";

        // Act
        List<String> actualList = new MessageProofreader().getCorrectedMessagesFromLocalFile(testDataPath, testReplacementPath);

        // Assert
        List<String> expectedList = Collections.emptyList();
        assertEquals(expectedList, actualList);
    }

    @Test
    @DisplayName("Проверка с использованием корректных входных данных")
    void testUsingCorrectInputData() {
        // Arrange
        String testDataPath = "src\\test\\resources\\proofreader\\data.json";
        String testReplacementPath = "src\\test\\resources\\proofreader\\replacement.json";

        // Act
        List<String> actualList = new MessageProofreader().getCorrectedMessagesFromLocalFile(testDataPath, testReplacementPath);

        // Assert
        List<String> expectedList = Collections.singletonList("The quick brown fox jumps over the lazy dog.");
        assertEquals(expectedList, actualList);
    }

    @Test
    @DisplayName("Проверка с использованием replacement.json без нужных замен")
    void testUsingIncorrectReplacementFile() {
        // Arrange
        String testDataPath = "src\\test\\resources\\proofreader\\data.json";
        String testReplacementPath = "src\\test\\resources\\proofreader\\replacementWithoutNecessaryReplacements.json";

        // Act
        List<String> actualList = new MessageProofreader().getCorrectedMessagesFromLocalFile(testDataPath, testReplacementPath);

        // Assert
        List<String> expectedList = Collections.singletonList("Th3 quick brown fox jumps ov3r th3 lazy dog.");
        assertEquals(expectedList, actualList);
    }

    @Test
    @DisplayName("Проверка где одна замена в себе содержит другую замену")
    void testWhereOneReplacementContainsAnotherReplacement() {
        // Arrange
        String testDataPath = "src\\test\\resources\\proofreader\\data.json";
        String testReplacementPath = "src\\test\\resources\\proofreader\\replacementWhereOneContainsAnother.json";

        // Act
        List<String> actualList = new MessageProofreader().getCorrectedMessagesFromLocalFile(testDataPath, testReplacementPath);

        // Assert
        List<String> expectedList = Collections.singletonList("The quick brown fox jumps over the wall.");
        assertEquals(expectedList, actualList);
    }
}