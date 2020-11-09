package quickcache.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static quickcache.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import quickcache.commons.exceptions.IllegalValueException;
import quickcache.commons.util.JsonUtil;
import quickcache.model.QuickCache;
import quickcache.testutil.TypicalFlashcards;

public class JsonSerializableQuickCacheTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableQuickCacheTest");
    private static final Path TYPICAL_FLASHCARDS_FILE = TEST_DATA_FOLDER.resolve("typicalFlashcardQuickCache.json");
    private static final Path INVALID_FLASHCARD_FILE = TEST_DATA_FOLDER.resolve("invalidFlashcardQuickCache.json");
    private static final Path DUPLICATE_FLASHCARD_FILE = TEST_DATA_FOLDER.resolve("duplicateFlashcardQuickCache.json");

    @Test
    public void toModelType_typicalFlashcardsFile_success() throws Exception {
        JsonSerializableQuickCache dataFromFile = JsonUtil.readJsonFile(TYPICAL_FLASHCARDS_FILE,
                JsonSerializableQuickCache.class).get();
        QuickCache quickCacheFromFile = dataFromFile.toModelType();
        QuickCache typicalFlashcardsQuickCache = TypicalFlashcards.getTypicalQuickCacheForStorage();
        assertEquals(quickCacheFromFile, typicalFlashcardsQuickCache);
    }

    @Test
    public void toModelType_invalidFlashcardFile_throwsIllegalValueException() throws Exception {
        JsonSerializableQuickCache dataFromFile = JsonUtil.readJsonFile(INVALID_FLASHCARD_FILE,
                JsonSerializableQuickCache.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateFlashcards_throwsIllegalValueException() throws Exception {
        JsonSerializableQuickCache dataFromFile = JsonUtil.readJsonFile(DUPLICATE_FLASHCARD_FILE,
                JsonSerializableQuickCache.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableQuickCache.MESSAGE_DUPLICATE_FLASHCARD,
                dataFromFile::toModelType);
    }

}
