package seedu.bagel.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.bagel.testutil.Assert.assertThrows;
import static seedu.bagel.testutil.TypicalFlashcards.ALICE;
import static seedu.bagel.testutil.TypicalFlashcards.HOON;
import static seedu.bagel.testutil.TypicalFlashcards.IDA;
import static seedu.bagel.testutil.TypicalFlashcards.getTypicalBagel;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.bagel.commons.exceptions.DataConversionException;
import seedu.bagel.model.Bagel;
import seedu.bagel.model.ReadOnlyBagel;

public class JsonBagelStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonBagelStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readBagel_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readBagel(null));
    }

    private java.util.Optional<ReadOnlyBagel> readBagel(String filePath) throws Exception {
        return new JsonBagelStorage(Paths.get(filePath)).readBagel(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readBagel("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readBagel("notJsonFormatBagel.json"));
    }

    @Test
    public void readBagel_invalidPersonBagel_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readBagel("invalidFlashcardBagel.json"));
    }

    @Test
    public void readBagel_invalidAndValidPersonBagel_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readBagel("invalidAndValidFlashcardBagel.json"));
    }

    @Test
    public void readAndSaveBagel_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempBagel.json");
        Bagel original = getTypicalBagel();
        JsonBagelStorage jsonBagelStorage = new JsonBagelStorage(filePath);

        // Save in new file and read back
        jsonBagelStorage.saveBagel(original, filePath);
        ReadOnlyBagel readBack = jsonBagelStorage.readBagel(filePath).get();
        assertEquals(original, new Bagel(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addFlashcard(HOON);
        original.removeFlashcard(ALICE);
        jsonBagelStorage.saveBagel(original, filePath);
        readBack = jsonBagelStorage.readBagel(filePath).get();
        assertEquals(original, new Bagel(readBack));

        // Save and read without specifying file path
        original.addFlashcard(IDA);
        jsonBagelStorage.saveBagel(original); // file path not specified
        readBack = jsonBagelStorage.readBagel().get(); // file path not specified
        assertEquals(original, new Bagel(readBack));

    }

    @Test
    public void saveBagel_nullBagel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveBagel(null, "SomeFile.json"));
    }

    /**
     * Saves {@code bagel} at the specified {@code filePath}.
     */
    private void saveBagel(ReadOnlyBagel bagel, String filePath) {
        try {
            new JsonBagelStorage(Paths.get(filePath))
                    .saveBagel(bagel, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveBagel_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveBagel(new Bagel(), null));
    }
}
