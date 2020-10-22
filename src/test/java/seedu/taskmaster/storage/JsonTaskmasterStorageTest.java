package seedu.taskmaster.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.taskmaster.testutil.Assert.assertThrows;
import static seedu.taskmaster.testutil.TypicalStudents.ALICE;
import static seedu.taskmaster.testutil.TypicalStudents.HOON;
import static seedu.taskmaster.testutil.TypicalStudents.IDA;
import static seedu.taskmaster.testutil.TypicalStudents.getTypicalTaskmaster;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.taskmaster.commons.exceptions.DataConversionException;
import seedu.taskmaster.model.ReadOnlyTaskmaster;
import seedu.taskmaster.model.Taskmaster;

public class JsonTaskmasterStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTaskmasterStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTaskmaster_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTaskmaster(null));
    }

    private java.util.Optional<ReadOnlyTaskmaster> readTaskmaster(String filePath) throws Exception {
        return new JsonTaskmasterStorage(Paths.get(filePath)).readTaskmaster(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTaskmaster("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readTaskmaster("notJsonFormatTaskmaster.json"));
    }

    @Test
    public void readTaskmaster_invalidStudentTaskmaster_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTaskmaster("invalidStudentTaskmaster.json"));
    }

    @Test
    public void readTaskmaster_invalidAndValidStudentTaskmaster_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTaskmaster("invalidAndValidStudentTaskmaster.json"));
    }

    @Test
    public void readAndSaveTaskmaster_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempTaskmaster.json");
        Taskmaster original = getTypicalTaskmaster();
        JsonTaskmasterStorage jsonTaskmasterStorage = new JsonTaskmasterStorage(filePath);

        // Save in new file and read back
        jsonTaskmasterStorage.saveTaskmaster(original, filePath);
        ReadOnlyTaskmaster readBack = jsonTaskmasterStorage.readTaskmaster(filePath).get();
        assertEquals(original, new Taskmaster(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addStudent(HOON);
        original.removeStudent(ALICE);
        jsonTaskmasterStorage.saveTaskmaster(original, filePath);
        readBack = jsonTaskmasterStorage.readTaskmaster(filePath).get();
        assertEquals(original, new Taskmaster(readBack));

        // Save and read without specifying file path
        original.addStudent(IDA);
        jsonTaskmasterStorage.saveTaskmaster(original); // file path not specified
        readBack = jsonTaskmasterStorage.readTaskmaster().get(); // file path not specified
        assertEquals(original, new Taskmaster(readBack));

    }

    @Test
    public void saveTaskmaster_nullTaskmaster_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTaskmaster(null, "SomeFile.json"));
    }

    /**
     * Saves {@code taskmaster} at the specified {@code filePath}.
     */
    private void saveTaskmaster(ReadOnlyTaskmaster taskmaster, String filePath) {
        try {
            new JsonTaskmasterStorage(Paths.get(filePath))
                    .saveTaskmaster(taskmaster, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTaskmaster_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTaskmaster(new Taskmaster(), null));
    }
}
