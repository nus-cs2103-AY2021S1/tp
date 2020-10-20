package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.ALICE;
import static seedu.address.testutil.TypicalPatients.HOON;
import static seedu.address.testutil.TypicalPatients.IDA;
import static seedu.address.testutil.TypicalPatients.getTypicalCliniCal;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.CliniCal;
import seedu.address.model.ReadOnlyCliniCal;

public class JsonCliniCalStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonCliniCalStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readCliniCal_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readCliniCal(null));
    }

    private java.util.Optional<ReadOnlyCliniCal> readCliniCal(String filePath) throws Exception {
        return new JsonCliniCalStorage(Paths.get(filePath)).readCliniCal(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readCliniCal("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readCliniCal("notJsonFormatCliniCal.json"));
    }

    @Test
    public void readCliniCal_invalidPatientCliniCal_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readCliniCal("invalidPatientCliniCal.json"));
    }

    @Test
    public void readCliniCal_invalidAndValidPatientCliniCal_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readCliniCal("invalidAndValidPatientCliniCal.json"));
    }

    @Test
    public void readAndSaveCliniCal_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempCliniCal.json");
        CliniCal original = getTypicalCliniCal();
        JsonCliniCalStorage jsonCliniCalStorage = new JsonCliniCalStorage(filePath);

        // Save in new file and read back
        jsonCliniCalStorage.saveCliniCal(original, filePath);
        ReadOnlyCliniCal readBack = jsonCliniCalStorage.readCliniCal(filePath).get();
        assertEquals(original, new CliniCal(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPatient(HOON);
        original.removePatient(ALICE);
        jsonCliniCalStorage.saveCliniCal(original, filePath);
        readBack = jsonCliniCalStorage.readCliniCal(filePath).get();
        assertEquals(original, new CliniCal(readBack));

        // Save and read without specifying file path
        original.addPatient(IDA);
        jsonCliniCalStorage.saveCliniCal(original); // file path not specified
        readBack = jsonCliniCalStorage.readCliniCal().get(); // file path not specified
        assertEquals(original, new CliniCal(readBack));

    }

    @Test
    public void saveCliniCal_nullCliniCal_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCliniCal(null, "SomeFile.json"));
    }

    /**
     * Saves {@code cliniCal} at the specified {@code filePath}.
     */
    private void saveCliniCal(ReadOnlyCliniCal cliniCal, String filePath) {
        try {
            new JsonCliniCalStorage(Paths.get(filePath))
                    .saveCliniCal(cliniCal, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveCliniCal_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCliniCal(new CliniCal(), null));
    }
}
