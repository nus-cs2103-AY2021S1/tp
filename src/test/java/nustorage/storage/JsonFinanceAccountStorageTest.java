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
import nustorage.model.FinanceAccount;
import nustorage.model.ReadOnlyFinanceAccount;


class JsonFinanceAccountStorageTest {

    private static final Path TEST_DATA_FOLDER =
        Paths.get("src", "test", "data", "JsonFinanceAccountStorageTest");

    private static final Path TEST_DATA_FILE =
        Paths.get("src", "test", "data", "JsonFinanceAccountStorageTest", "financeAccount.json");

    private static final Path TEST_DATA_TARGET_FILE =
        Paths.get("src", "test", "data", "JsonFinanceAccountStorageTest", "testSave.json");


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
    void getFinanceAccountFilePath_validFilePath_returnsCorrectFilePath() {
        JsonFinanceAccountStorage testFinanceAccountStorage = new JsonFinanceAccountStorage(TEST_DATA_FOLDER);
        assertEquals(TEST_DATA_FOLDER, testFinanceAccountStorage.getFinanceAccountFilePath());
    }


    private Optional<ReadOnlyFinanceAccount> readFinanceAccount(Path filePath)
        throws NullPointerException, DataConversionException {
        return new JsonFinanceAccountStorage(TEST_DATA_FOLDER).readFinanceAccount(filePath);
    }


    private Optional<ReadOnlyFinanceAccount> readFinanceAccount() throws NullPointerException, DataConversionException {
        return new JsonFinanceAccountStorage(TEST_DATA_FILE).readFinanceAccount();
    }


    @Test
    void readFinanceAccount_validInputs_returnsNonEmptyOptional() {
        assertTrue(() -> {
            try {
                return readFinanceAccount().isPresent();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
    }


    @Test
    void readFinanceAccount_validFilePath_returnsCorrectFinanceAccount() throws DataConversionException {
        Optional<ReadOnlyFinanceAccount> testOptionalFinanceAccount =
            new JsonFinanceAccountStorage(TEST_DATA_FOLDER).readFinanceAccount(TEST_DATA_FILE);
        assertEquals(testOptionalFinanceAccount, readFinanceAccount(TEST_DATA_FILE));
    }


    @Test
    void readFinanceAccount_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readFinanceAccount(null));
    }


    @Test
    void readFinanceAccount_invalidJsonFilePath_returnsEmptyOptional() {
        // nonExistentAccount.json is a file that does not exist in the directory
        assertTrue(() -> {
            try {
                return readFinanceAccount(
                    Paths.get(String.valueOf(TEST_DATA_FOLDER), "nonExistentAccount.json")).isEmpty();
            } catch (DataConversionException e) {
                return false;
            }
        });
    }


    @Test
    void readFinanceAccount_invalidJsonFile_throwsDataConversionException() {
        // invalidRecord.json is a file that does not follow the json file convention
        assertThrows(DataConversionException.class, () ->
            readFinanceAccount(Paths.get(String.valueOf(TEST_DATA_FOLDER), "invalidRecord.json"))
        );
    }


    @Test
    void readFinanceAccount_invalidFinanceAccountFile_throwsDataConversionException() {
        // invalidRecord.json is a file that does not follow the json file convention
        assertThrows(DataConversionException.class, () ->
            readFinanceAccount(Paths.get(String.valueOf(TEST_DATA_FOLDER), "invalidFinanceAccount.json"))
        );
    }


    void saveFinanceAccount(ReadOnlyFinanceAccount toSave) {
        try {
            new JsonFinanceAccountStorage(TEST_DATA_FOLDER).saveFinanceAccount(toSave);
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }


    void saveFinanceAccount(ReadOnlyFinanceAccount toSave, Path filePath) {
        try {
            new JsonFinanceAccountStorage(TEST_DATA_FOLDER)
                .saveFinanceAccount(toSave, filePath);
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }


    @Test
    void saveFinanceAccount_nullFinanceAccount_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFinanceAccount(null,
            Paths.get(String.valueOf(TEST_DATA_FOLDER), "someFinanceAccount.json"))
        );
    }


    @Test
    void saveFinanceAccount2_nullFinanceAccount_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFinanceAccount(null));
    }


    @Test
    void saveFinanceAccount_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFinanceAccount(new FinanceAccount(), null));
    }


    @Test
    void saveFinanceAccount_nonExistentFilePath_fileCreatedAndSaved() throws DataConversionException, IOException {
        Optional<ReadOnlyFinanceAccount> testFinanceAccount = readFinanceAccount(TEST_DATA_FILE);
        assertTrue(testFinanceAccount.isPresent());

        JsonFinanceAccountStorage testFinanceAccountStorage = new JsonFinanceAccountStorage(TEST_DATA_TARGET_FILE);
        testFinanceAccountStorage.saveFinanceAccount(testFinanceAccount.get());

        Optional<ReadOnlyFinanceAccount> readBackAccount = testFinanceAccountStorage.readFinanceAccount();
        assertTrue(readBackAccount.isPresent());

        assertEquals(testFinanceAccount.get(), readBackAccount.get());
    }

}
