package nustorage.storage;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import nustorage.commons.exceptions.DataConversionException;
import nustorage.model.Inventory;
import nustorage.model.ReadOnlyInventory;


class JsonInventoryStorageTest {

    private static final Path TEST_DATA_FOLDER =
        Paths.get("src", "test", "data", "JsonInventoryStorageTest");

    private static final Path TEST_DATA_FILE =
        Paths.get("src", "test", "data", "JsonInventoryStorageTest", "inventory.json");

    private static final Path TEST_DATA_TARGET_FILE =
        Paths.get("src", "test", "data", "JsonInventoryStorageTest", "testSave.json");


    @BeforeAll
    @AfterAll
    public static void removeSaveTargetFile() throws IOException {
        try {
            // Delete the save target file if it exists before and after the test
            Files.delete(TEST_DATA_TARGET_FILE);
        } catch (NoSuchFileException ignored) {
            // ignored
        }
    }


    @Test
    void getInventoryFilePath_validFilePath_returnsCorrectFilePath() {
        JsonInventoryStorage testFinanceAccountStorage = new JsonInventoryStorage(TEST_DATA_FOLDER);
        assertEquals(TEST_DATA_FOLDER, testFinanceAccountStorage.getInventoryFilePath());
    }


    private Optional<ReadOnlyInventory> readInventory(Path filePath)
        throws NullPointerException, DataConversionException {
        return new JsonInventoryStorage(TEST_DATA_FOLDER).readInventory(filePath);
    }


    private Optional<ReadOnlyInventory> readInventory() throws NullPointerException, DataConversionException {
        return new JsonInventoryStorage(TEST_DATA_FILE).readInventory();
    }


    @Test
    void readInventory_validInputs_returnsNonEmptyOptional() {
        assertTrue(() -> {
            try {
                return readInventory().isPresent();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
    }


    @Test
    void readInventory_validFilePath_returnsCorrectFinanceAccount() throws DataConversionException {
        Optional<ReadOnlyInventory> testOptionalFinanceAccount =
            new JsonInventoryStorage(TEST_DATA_FOLDER).readInventory(TEST_DATA_FILE);
        assertEquals(testOptionalFinanceAccount, readInventory(TEST_DATA_FILE));
    }


    @Test
    void readInventory_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readInventory(null));
    }


    @Test
    void readInventory_invalidJsonFilePath_returnsEmptyOptional() {
        // nonExistentAccount.json is a file that does not exist in the directory
        assertTrue(() -> {
            try {
                return readInventory(
                    Paths.get(String.valueOf(TEST_DATA_FOLDER), "nonExistentAccount.json")).isEmpty();
            } catch (DataConversionException e) {
                return false;
            }
        });
    }


    @Test
    void readInventory_invalidJsonFile_throwsDataConversionException() {
        // invalidRecord.json is a file that does not follow the json file convention
        assertThrows(DataConversionException.class, () ->
            readInventory(Paths.get(String.valueOf(TEST_DATA_FOLDER), "invalidRecord.json")));
    }


    @Test
    void readInventory_invalidFinanceAccountFile_throwsDataConversionException() {
        // invalidRecord.json is a file that does not follow the json file convention
        assertThrows(DataConversionException.class, () ->
            readInventory(Paths.get(String.valueOf(TEST_DATA_FOLDER), "invalidInventory.json")));
    }


    @Test
    void readInventory_invalidFinanceAccountFile2_throwsDataConversionException() {
        // invalidRecord.json is a file that does not follow the json file convention
        assertThrows(DataConversionException.class, () ->
            readInventory(Paths.get(String.valueOf(TEST_DATA_FOLDER), "invalidInventory2.json")));
    }


    void saveFinanceAccount(ReadOnlyInventory toSave) {
        try {
            new JsonInventoryStorage(TEST_DATA_FOLDER).saveInventory(toSave);
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }


    void saveFinanceAccount(ReadOnlyInventory toSave, Path filePath) {
        try {
            new JsonInventoryStorage(TEST_DATA_FOLDER)
                .saveInventory(toSave, filePath);
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }


    @Test
    void saveFinanceAccount_nullFinanceAccount_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFinanceAccount(null,
            Paths.get(String.valueOf(TEST_DATA_FOLDER), "someFinanceAccount.json")));
    }


    @Test
    void saveFinanceAccount2_nullFinanceAccount_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFinanceAccount(null));
    }


    @Test
    void saveFinanceAccount_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFinanceAccount(new Inventory(), null));
    }


    @Test
    void saveFinanceAccount_nonExistentFilePath_fileCreatedAndSaved() throws DataConversionException, IOException {
        Optional<ReadOnlyInventory> testFinanceAccount = readInventory(TEST_DATA_FILE);
        assertTrue(testFinanceAccount.isPresent());

        JsonInventoryStorage testFinanceAccountStorage = new JsonInventoryStorage(TEST_DATA_TARGET_FILE);
        testFinanceAccountStorage.saveInventory(testFinanceAccount.get());

        Optional<ReadOnlyInventory> readBackAccount = testFinanceAccountStorage.readInventory();
        assertTrue(readBackAccount.isPresent());

        assertEquals(testFinanceAccount.get(), readBackAccount.get());
    }

}
