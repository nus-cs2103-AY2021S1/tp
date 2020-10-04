package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyFlashcardDeck;

public class JsonFlashcardDeckStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonFlashcardDeckStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readFlashcardDeck_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readFlashcardDeck(null));
    }

    private java.util.Optional<ReadOnlyFlashcardDeck> readFlashcardDeck(String filePath) throws Exception {
        return new JsonFlashcardDeckStorage(Paths.get(filePath))
                .readFlashcardDeck(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readFlashcardDeck("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readFlashcardDeck("notJsonFormatFlashcardDeck.json"));
    }

    @Test
    public void readFlashcardDeck_invalidFlashcard_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readFlashcardDeck("invalidFlashCardFlashcardDeck.json"));
    }

    @Test
    public void readFlashcardDeck_invalidAndValidFlashcards_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readFlashcardDeck("invalidAndValidFlashcardsFlashcardDeck.json"));
    }
}

