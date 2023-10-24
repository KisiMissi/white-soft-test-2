package org.kaoden.io;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest {

    @Test
    @DisplayName("Чтение несуществующего data.json файла")
    void readNonExistDataFile() {
        // Arrange
        String nonExistDataFile = "nonExistDataFile.json";

        // Assert
        assertThrows(RuntimeException.class, () -> JsonReader.readUserMessages(nonExistDataFile));
    }

    @Test
    @DisplayName("Чтения пустого data.json файла")
    void readEmptyDataFile() {
        // Arrange
        String emptyDataFile = "src\\test\\resources\\read\\dataEmpty.json";

        //Act
        String[] actual = JsonReader.readUserMessages(emptyDataFile);

        //Assert
        String[] expected = new String[] {};
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Чтение несуществующего replacement.json файла")
    void readNonExistReplacementFile() {
        // Arrange
        String nonExistReplacementFile = "src\\test\\resources\\read\\someReplacement.json";

        // Assert
        assertThrows(RuntimeException.class, () -> JsonReader.readOriginalMessages(nonExistReplacementFile));
    }

    @Test
    @DisplayName("Проверка чтения пустого replacement.json файла")
    void readEmptyReplacementFile() {
        // Arrange
        String emptyReplacementFile = "src\\test\\resources\\read\\replacementEmpty.json";

        //Act
        String[] actual = JsonReader.readUserMessages(emptyReplacementFile);

        //Assert
        String[] expected = new String[] {};
        assertEquals(expected.length, actual.length);
    }

    @Test
    @DisplayName("Чтение неправильно отформатированного data.json файла")
    void readIncorrectlyFormattedFile() {
        // Arrange
        String incorrectDataFile = "src\\test\\resources\\read\\dataIncorrect.json";

        // Assert
        assertThrows(RuntimeException.class, () -> JsonReader.readUserMessages(incorrectDataFile));
    }

    @Test
    @DisplayName("Чтение data.json с некорректными данными")
    void readDataFileWithIncorrectData() {
        // Arrange
        String dataFileWithIncorrectData = "src\\test\\resources\\read\\dataWithIncorrectData.json";

        // Assert
        assertThrows(RuntimeException.class, () -> JsonReader.readUserMessages(dataFileWithIncorrectData));
    }
}