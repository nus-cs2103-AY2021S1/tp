package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalClientList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ClientList;
import seedu.address.model.ReadOnlyClientList;

public class JsonClientListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonClientListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readClientList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readClientList(null));
    }

    private java.util.Optional<ReadOnlyClientList> readClientList(String filePath) throws Exception {
        return new JsonClientListStorage(Paths.get(filePath)).readClientList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readClientList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readClientList("notJsonFormatClientList.json"));
    }

    @Test
    public void readClientList_invalidPersonClientList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readClientList("invalidPersonClientList.json"));
    }

    @Test
    public void readClientList_invalidAndValidPersonClientList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readClientList("invalidAndValidPersonClientList.json"));
    }

    @Test
    public void readAndSaveClientList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempClientList.json");
        ClientList original = getTypicalClientList();
        JsonClientListStorage jsonClientListStorage = new JsonClientListStorage(filePath);

        // Save in new file and read back
        jsonClientListStorage.saveClientList(original, filePath);
        ReadOnlyClientList readBack = jsonClientListStorage.readClientList(filePath).get();
        assertEquals(original, new ClientList(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonClientListStorage.saveClientList(original, filePath);
        readBack = jsonClientListStorage.readClientList(filePath).get();
        assertEquals(original, new ClientList(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonClientListStorage.saveClientList(original); // file path not specified
        readBack = jsonClientListStorage.readClientList().get(); // file path not specified
        assertEquals(original, new ClientList(readBack));

    }

    @Test
    public void saveClientList_nullClientList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveClientList(null, "SomeFile.json"));
    }

    /**
     * Saves {@code clientList} at the specified {@code filePath}.
     */
    private void saveClientList(ReadOnlyClientList clientList, String filePath) {
        try {
            new JsonClientListStorage(Paths.get(filePath))
                    .saveClientList(clientList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveClientList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveClientList(new ClientList(), null));
    }
}
