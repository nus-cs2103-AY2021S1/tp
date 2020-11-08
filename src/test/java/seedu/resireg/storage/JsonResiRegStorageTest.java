package seedu.resireg.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.resireg.testutil.Assert.assertThrows;
import static seedu.resireg.testutil.TypicalStudents.ALICE;
import static seedu.resireg.testutil.TypicalStudents.HOON;
import static seedu.resireg.testutil.TypicalStudents.IDA;
import static seedu.resireg.testutil.TypicalStudents.getTypicalResiReg;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.resireg.commons.exceptions.DataConversionException;
import seedu.resireg.model.ReadOnlyResiReg;
import seedu.resireg.model.ResiReg;

public class JsonResiRegStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonResiRegStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readResiReg_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readResiReg(null));
    }

    private java.util.Optional<ReadOnlyResiReg> readResiReg(String filePath) throws Exception {
        return new JsonResiRegStorage(Paths.get(filePath)).readResiReg(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
            ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
            : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readResiReg("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readResiReg("notJsonFormatResiReg.json"));
    }

    @Test
    public void readResiReg_invalidStudentResiReg_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readResiReg("invalidStudentResiReg.json"));
    }

    @Test
    public void readResiReg_invalidAndValidStudentResiReg_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readResiReg("invalidAndValidStudentResiReg.json"));
    }

    @Test
    public void readAndSaveResiReg_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempResiReg.json");
        ResiReg original = getTypicalResiReg();
        JsonResiRegStorage jsonResiRegStorage = new JsonResiRegStorage(filePath);

        // Save in new file and read back
        jsonResiRegStorage.saveResiReg(original, filePath);
        ReadOnlyResiReg readBack = jsonResiRegStorage.readResiReg(filePath).get();
        assertEquals(original, new ResiReg(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addStudent(HOON);
        original.removeStudent(ALICE);
        jsonResiRegStorage.saveResiReg(original, filePath);
        readBack = jsonResiRegStorage.readResiReg(filePath).get();
        assertEquals(original, new ResiReg(readBack));

        // Save and read without specifying file path
        original.addStudent(IDA);
        jsonResiRegStorage.saveResiReg(original); // file path not specified
        readBack = jsonResiRegStorage.readResiReg().get(); // file path not specified
        assertEquals(original, new ResiReg(readBack));

    }

    @Test
    public void saveResiReg_nullResiReg_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveResiReg(null, "SomeFile.json"));
    }

    /**
     * Saves {@code resiReg} at the specified {@code filePath}.
     */
    private void saveResiReg(ReadOnlyResiReg resiReg, String filePath) {
        try {
            new JsonResiRegStorage(Paths.get(filePath))
                .saveResiReg(resiReg, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveResiReg_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveResiReg(new ResiReg(), null));
    }

    /**
     * Archives {@code resiReg}.
     */
    private void archiveResiReg(ReadOnlyResiReg resiReg, String filePath) {
        try {
            new JsonResiRegStorage(Paths.get(filePath))
                    .archiveResiReg(resiReg);
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void archiveResiReg_nullResiReg_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> archiveResiReg(null, "SomeFile.json"));
    }

}
