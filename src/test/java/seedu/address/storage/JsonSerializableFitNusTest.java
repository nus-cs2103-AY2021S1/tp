package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.FitNus;
import seedu.address.testutil.TypicalLessons;
import seedu.address.testutil.TypicalSlots;

public class JsonSerializableFitNusTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableFitNusTest");

    private static final Path TYPICAL_LESSONS_FILE = TEST_DATA_FOLDER.resolve("typicalLessonsFitNus.json");
    private static final Path INVALID_LESSON_FILE = TEST_DATA_FOLDER.resolve("invalidLessonFitNus.json");
    private static final Path DUPLICATE_LESSON_FILE = TEST_DATA_FOLDER.resolve("duplicateLessonFitNus.json");

    private static final Path TYPICAL_SLOTS_FILE = TEST_DATA_FOLDER.resolve("typicalSlotsFitNus.json");
    private static final Path INVALID_SLOT_FILE = TEST_DATA_FOLDER.resolve("invalidSlotFitNus.json");
    private static final Path DUPLICATE_SLOT_FILE = TEST_DATA_FOLDER.resolve("duplicateSlotFitNus.json");
    private static final Path OVERLAP_SLOT_FILE = TEST_DATA_FOLDER.resolve("overlapSlotFitNus.json");

    @Test
    public void toModelType_typicalLessonsFile_success() throws Exception {
        JsonSerializableFitNus dataFromFile = JsonUtil.readJsonFile(TYPICAL_LESSONS_FILE,
                JsonSerializableFitNus.class).get();
        FitNus fitNusFromFile = dataFromFile.toModelType();
        FitNus typicalLessonsFitNus = TypicalLessons.getTypicalFitNus();
        assertEquals(fitNusFromFile, typicalLessonsFitNus);
    }

    @Test
    public void toModelType_invalidLessonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableFitNus dataFromFile = JsonUtil.readJsonFile(INVALID_LESSON_FILE,
                JsonSerializableFitNus.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateLessons_throwsIllegalValueException() throws Exception {
        JsonSerializableFitNus dataFromFile = JsonUtil.readJsonFile(DUPLICATE_LESSON_FILE,
                JsonSerializableFitNus.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableFitNus.MESSAGE_DUPLICATE_LESSON,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_typicalSlotsFile_success() throws Exception {
        JsonSerializableFitNus dataFromFile = JsonUtil.readJsonFile(TYPICAL_SLOTS_FILE,
                JsonSerializableFitNus.class).get();
        FitNus actualFitNus = dataFromFile.toModelType();
        FitNus expectedFitNus = TypicalSlots.getTypicalFitNus();
        assertEquals(expectedFitNus, actualFitNus);
    }

    @Test
    public void toModelType_invalidSlotsFile_throwsIllegalValueException() throws Exception {
        JsonSerializableFitNus dataFromFile = JsonUtil.readJsonFile(INVALID_SLOT_FILE,
                JsonSerializableFitNus.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateSlots_throwsIllegalValueException() throws Exception {
        JsonSerializableFitNus dataFromFile = JsonUtil.readJsonFile(DUPLICATE_SLOT_FILE,
                JsonSerializableFitNus.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableFitNus.MESSAGE_DUPLICATE_SLOT,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_overlapSlots_throwsIllegalValueException() throws Exception {
        JsonSerializableFitNus dataFromFile = JsonUtil.readJsonFile(OVERLAP_SLOT_FILE,
                JsonSerializableFitNus.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableFitNus.MESSAGE_OVERLAP_SLOT,
                dataFromFile::toModelType);
    }

}
