package dev.mmieckowski;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.InputMismatchException;
import java.util.List;

class DataLoaderTest {
    private static DataLoader dataLoader;

    @BeforeAll
    static void setUp() {
        dataLoader = new DataLoader();
    }

    @DisplayName("Invalid path to data Test")
    @ParameterizedTest(name = "{displayName} : {arguments}")
    @ValueSource(strings = {"", " ", "asdf"})
    void getDataFromFileWillReturnEmptyListIfPathEmptyBlankOrInvalidTest(String path) {
        List<PersonPhone> data = dataLoader.getDataFromFile(path);
        assertNotNull(data);
        assertTrue(data.isEmpty());
    }

    @DisplayName("Valid path and data format Test")
    @Test
    void getDataFromFileWillReturnNonEmptyListOfPersonPhoneObjectsIfPathIsValidAndDataProperlyFormatted() {
        String path = "src/test/resources/testData.csv";
        List<PersonPhone> data = dataLoader.getDataFromFile(path);
        assertNotNull(data);
        assertFalse(data.isEmpty());
    }

    @DisplayName("Valid path and invalid data format Test")
    @Test
    void getDataFromFileWillThrowExceptionIfPathIsValidButDataNotProperlyFormatted() {
        String path = "src/test/resources/testWrongFormatOfData.csv";
        assertThrows(InputMismatchException.class, () -> dataLoader.getDataFromFile(path));
    }

    @DisplayName("Invalid path to targets Test")
    @ParameterizedTest(name = "{displayName} : {arguments}")
    @ValueSource(strings = {"", " ", "asdf"})
    void getTargetsFromFileWillReturnEmptyListIfPathEmptyBlankOrInvalidTest(String path) {
        List<String> data = dataLoader.getTargetsFromFile(path);
        assertNotNull(data);
        assertTrue(data.isEmpty());
    }

    @DisplayName("Valid path and invalid target data format")
    @Test
    void getTargetsFromFileWillReturnNonEmptyListOfStringsIfPathIsValidAndDataProperlyFormatted() {
        String path = "src/test/resources/testTargets.csv";
        List<String> data = dataLoader.getTargetsFromFile(path);
        assertNotNull(data);
        assertFalse(data.isEmpty());
    }
}