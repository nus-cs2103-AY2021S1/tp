package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalProjact.getTypicalProjact;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Projact;
import seedu.address.model.ReadOnlyProjact;

public class JsonProjactStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonProjactStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readProjact_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readProjact(null));
    }

    private java.util.Optional<ReadOnlyProjact> readProjact(String filePath) throws Exception {
        return new JsonProjactStorage(Paths.get(filePath)).readProjact(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readProjact("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readProjact("notJsonFormatProjact.json"));
    }

    @Test
    public void readProjact_invalidPersonProjact_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readProjact("invalidPersonProjact.json"));
    }

    @Test
    public void readProjact_invalidAndValidPersonProjact_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readProjact("invalidAndValidPersonProjact.json"));
    }

    @Test
    public void readAndSaveProjact_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempProjact.json");
        Projact original = getTypicalProjact();
        JsonProjactStorage jsonProjactStorage = new JsonProjactStorage(filePath);

        // Save in new file and read back
        jsonProjactStorage.saveProjact(original, filePath);
        ReadOnlyProjact readBack = jsonProjactStorage.readProjact(filePath).get();
        assertEquals(original, new Projact(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonProjactStorage.saveProjact(original, filePath);
        readBack = jsonProjactStorage.readProjact(filePath).get();
        assertEquals(original, new Projact(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonProjactStorage.saveProjact(original); // file path not specified
        readBack = jsonProjactStorage.readProjact().get(); // file path not specified
        assertEquals(original, new Projact(readBack));

    }

    @Test
    public void saveProjact_nullProjact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveProjact(null, "SomeFile.json"));
    }

    /**
     * Saves {@code projact} at the specified {@code filePath}.
     */
    private void saveProjact(ReadOnlyProjact projact, String filePath) {
        try {
            new JsonProjactStorage(Paths.get(filePath))
                    .saveProjact(projact, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveProjact_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveProjact(new Projact(), null));
    }
}
