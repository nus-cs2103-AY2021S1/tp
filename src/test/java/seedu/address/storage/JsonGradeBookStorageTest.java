package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.COM_ORG;
import static seedu.address.testutil.TypicalModules.GEH;
import static seedu.address.testutil.TypicalModules.GER;
import static seedu.address.testutil.TypicalModules.getTypicalGradeBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.GradeBook;
import seedu.address.model.ReadOnlyGradeBook;
import seedu.address.model.module.GoalTarget;

public class JsonGradeBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonGradeBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readGradeBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readGradeBook(null));
    }

    private java.util.Optional<ReadOnlyGradeBook> readGradeBook(String filePath) throws Exception {
        return new JsonGradeBookStorage(Paths.get(filePath)).readGradeBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readGradeBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readGradeBook("notJsonFormatGradeBook.json"));
    }

    @Test
    public void readGradeBook_invalidModuleGradeBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readGradeBook("invalidModuleGradeBook.json"));
    }

    @Test
    public void readAndSaveGradeBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        GradeBook original = getTypicalGradeBook();
        JsonGradeBookStorage jsonGradeBookStorage = new JsonGradeBookStorage(filePath);
        GoalTarget goalTarget = new GoalTarget(2);

        // Save in new file and read back
        jsonGradeBookStorage.saveGradeBook(original, filePath, goalTarget);
        ReadOnlyGradeBook readBack = jsonGradeBookStorage.readGradeBook(filePath).get();
        assertEquals(original, new GradeBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addModule(GEH);
        original.removeModule(COM_ORG);
        jsonGradeBookStorage.saveGradeBook(original, filePath, goalTarget);
        readBack = jsonGradeBookStorage.readGradeBook(filePath).get();
        assertEquals(original, new GradeBook(readBack));

        // Save and read without specifying file path
        original.addModule(GER);
        jsonGradeBookStorage.saveGradeBook(original, goalTarget); // file path not specified
        readBack = jsonGradeBookStorage.readGradeBook().get(); // file path not specified
        assertEquals(original, new GradeBook(readBack));

    }

    @Test
    public void saveGradeBook_nullGradeBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveGradeBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code gradeBook} at the specified {@code filePath}.
     */
    private void saveGradeBook(ReadOnlyGradeBook gradeBook, String filePath) {
        try {
            new JsonGradeBookStorage(Paths.get(filePath))
                    .saveGradeBook(gradeBook, addToTestDataPathIfNotNull(filePath), new GoalTarget());
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveGradeBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveGradeBook(new GradeBook(), null));
    }
}
