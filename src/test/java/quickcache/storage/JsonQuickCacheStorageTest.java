package quickcache.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static quickcache.testutil.Assert.assertThrows;
import static quickcache.testutil.TypicalFlashcards.RANDOM1;
import static quickcache.testutil.TypicalFlashcards.RANDOM2;
import static quickcache.testutil.TypicalFlashcards.RANDOM3;
import static quickcache.testutil.TypicalFlashcards.getTypicalQuickCacheForStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import quickcache.commons.exceptions.DataConversionException;
import quickcache.model.QuickCache;
import quickcache.model.ReadOnlyQuickCache;

public class JsonQuickCacheStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonQuickCacheStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readQuickCache_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyQuickCache> readAddressBook(String filePath) throws Exception {
        return new JsonQuickCacheStorage(Paths.get(filePath)).readQuickCache(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAddressBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readAddressBook("notJsonFormatQuickCache.json"));
    }

    @Test
    public void readQuickCache_invalidFlashcardQuickCache_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidFlashcardQuickCache.json"));
    }

    @Test
    public void readQuickCache_invalidAndValidFlashcardQuickCache_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidAndValidFlashcardQuickCache.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        QuickCache original = getTypicalQuickCacheForStorage();
        JsonQuickCacheStorage jsonQuickCacheStorage = new JsonQuickCacheStorage(filePath);

        // Save in new file and read back
        jsonQuickCacheStorage.saveQuickCache(original, filePath);
        ReadOnlyQuickCache readBack = jsonQuickCacheStorage.readQuickCache(filePath).get();
        assertEquals(original, new QuickCache(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addFlashcard(RANDOM2);
        original.removeFlashcard(RANDOM1);
        jsonQuickCacheStorage.saveQuickCache(original, filePath);
        readBack = jsonQuickCacheStorage.readQuickCache(filePath).get();
        assertEquals(original, new QuickCache(readBack));

        // Save and read without specifying file path
        original.addFlashcard(RANDOM3);
        jsonQuickCacheStorage.saveQuickCache(original); // file path not specified
        readBack = jsonQuickCacheStorage.readQuickCache().get(); // file path not specified
        assertEquals(original, new QuickCache(readBack));

    }

    @Test
    public void saveQuickCache_nullQuickCache_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveQuickCache(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveQuickCache(ReadOnlyQuickCache addressBook, String filePath) {
        try {
            new JsonQuickCacheStorage(Paths.get(filePath))
                    .saveQuickCache(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveQuickCache_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveQuickCache(new QuickCache(), null));
    }
}
