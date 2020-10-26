package seedu.zookeep.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.zookeep.testutil.Assert.assertThrows;
import static seedu.zookeep.testutil.TypicalAnimals.AHMENG;
import static seedu.zookeep.testutil.TypicalAnimals.JIAJIA;
import static seedu.zookeep.testutil.TypicalAnimals.KAIKAI;
import static seedu.zookeep.testutil.TypicalAnimals.getTypicalZooKeepBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.zookeep.commons.exceptions.DataConversionException;
import seedu.zookeep.model.ReadOnlyZooKeepBook;
import seedu.zookeep.model.ZooKeepBook;

public class JsonZooKeepBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonZooKeepBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readZooKeepBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readZooKeepBook(null));
    }

    private java.util.Optional<ReadOnlyZooKeepBook> readZooKeepBook(String filePath) throws Exception {
        return new JsonZooKeepBookStorage(Paths.get(filePath)).readZooKeepBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readZooKeepBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readZooKeepBook("notJsonFormatZooKeepBook.json"));
    }

    @Test
    public void readZooKeepBook_invalidAnimalZooKeepBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readZooKeepBook("invalidAnimalZooKeepBook.json"));
    }

    @Test
    public void readZooKeepBook_invalidAndValidAnimalZooKeepBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readZooKeepBook("invalidAndValidAnimalZooKeepBook.json"));
    }

    @Test
    public void readAndSaveZooKeepBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempZooKeepBook.json");
        ZooKeepBook original = getTypicalZooKeepBook();
        JsonZooKeepBookStorage jsonZooKeepBookStorage = new JsonZooKeepBookStorage(filePath);

        // Save in new file and read back
        jsonZooKeepBookStorage.saveZooKeepBook(original, filePath);
        ReadOnlyZooKeepBook readBack = jsonZooKeepBookStorage.readZooKeepBook(filePath).get();
        assertEquals(original, new ZooKeepBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addAnimal(JIAJIA);
        original.removeAnimal(AHMENG);
        jsonZooKeepBookStorage.saveZooKeepBook(original, filePath);
        readBack = jsonZooKeepBookStorage.readZooKeepBook(filePath).get();
        assertEquals(original, new ZooKeepBook(readBack));

        // Save and read without specifying file path
        original.addAnimal(KAIKAI);
        jsonZooKeepBookStorage.saveZooKeepBook(original); // file path not specified
        readBack = jsonZooKeepBookStorage.readZooKeepBook().get(); // file path not specified
        assertEquals(original, new ZooKeepBook(readBack));

    }

    @Test
    public void saveZooKeepBook_nullZooKeepBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveZooKeepBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code zooKeepBook} at the specified {@code filePath}.
     */
    private void saveZooKeepBook(ReadOnlyZooKeepBook zooKeepBook, String filePath) {
        try {
            new JsonZooKeepBookStorage(Paths.get(filePath))
                    .saveZooKeepBook(zooKeepBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveZooKeepBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveZooKeepBook(new ZooKeepBook(), null));
    }
}
