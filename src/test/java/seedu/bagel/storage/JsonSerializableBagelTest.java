package seedu.bagel.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.bagel.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.bagel.commons.exceptions.IllegalValueException;
import seedu.bagel.commons.util.JsonUtil;
import seedu.bagel.model.Bagel;
import seedu.bagel.testutil.TypicalFlashcards;

public class JsonSerializableBagelTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableBagelTest");
    private static final Path TYPICAL_FLASHCARDS_FILE = TEST_DATA_FOLDER.resolve("typicalFlashcardsBagel.json");
    private static final Path INVALID_FLASHCARD_FILE = TEST_DATA_FOLDER.resolve("invalidFlashcardBagel.json");
    private static final Path DUPLICATE_FLASHCARD_FILE = TEST_DATA_FOLDER.resolve("duplicateFlashcardBagel.json");

    @Test
    public void toModelType_typicalFlashcardsFile_success() throws Exception {
        JsonSerializableBagel dataFromFile = JsonUtil.readJsonFile(TYPICAL_FLASHCARDS_FILE,
                JsonSerializableBagel.class).get();
        Bagel bagelFromFile = dataFromFile.toModelType();
        Bagel typicalFlashcardsBagel = TypicalFlashcards.getTypicalBagel();
        assertEquals(bagelFromFile, typicalFlashcardsBagel);
    }

    @Test
    public void toModelType_invalidFlashcardFile_throwsIllegalValueException() throws Exception {
        JsonSerializableBagel dataFromFile = JsonUtil.readJsonFile(INVALID_FLASHCARD_FILE,
                JsonSerializableBagel.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateFlashcards_throwsIllegalValueException() throws Exception {
        JsonSerializableBagel dataFromFile = JsonUtil.readJsonFile(DUPLICATE_FLASHCARD_FILE,
                JsonSerializableBagel.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableBagel.MESSAGE_DUPLICATE_FLASHCARD,
                dataFromFile::toModelType);
    }

}
