package seedu.forgetfulnus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.forgetfulnus.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.forgetfulnus.commons.exceptions.IllegalValueException;
import seedu.forgetfulnus.commons.util.JsonUtil;
import seedu.forgetfulnus.model.Glossary;
import seedu.forgetfulnus.testutil.TypicalFlashCards;

public class JsonSerializableGlossaryTest {

    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonSerializableGlossaryTest");
    private static final Path TYPICAL_FLASHCARDS_FILE =
            TEST_DATA_FOLDER.resolve("typicalFlashCardsGlossary.json");
    private static final Path INVALID_FLASHCARD_FILE =
            TEST_DATA_FOLDER.resolve("invalidFlashCardGlossary.json");
    private static final Path DUPLICATE_FLASHCARD_FILE =
            TEST_DATA_FOLDER.resolve("duplicateFlashCardGlossary.json");

    @Test
    public void toModelType_typicalFlashCardsFile_success() throws Exception {
        JsonSerializableGlossary dataFromFile = JsonUtil.readJsonFile(TYPICAL_FLASHCARDS_FILE,
                JsonSerializableGlossary.class).get();
        Glossary glossaryFromFile = dataFromFile.toModelType();
        Glossary typicalFlashCardsGlossary = TypicalFlashCards.getTypicalGlossary();
        assertEquals(glossaryFromFile, typicalFlashCardsGlossary);
    }

    @Test

    public void toModelType_invalidFlashCardFile_throwsIllegalValueException() throws Exception {
        JsonSerializableGlossary dataFromFile = JsonUtil.readJsonFile(INVALID_FLASHCARD_FILE,
                JsonSerializableGlossary.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateFlashcard_throwsIllegalValueException() throws Exception {

        JsonSerializableGlossary dataFromFile = JsonUtil.readJsonFile(DUPLICATE_FLASHCARD_FILE,
                JsonSerializableGlossary.class).get();
        assertThrows(IllegalValueException.class,
                JsonSerializableGlossary.MESSAGE_DUPLICATE_FLASHCARD,
                dataFromFile::toModelType);
    }

}
