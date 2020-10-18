package seedu.pivot.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.pivot.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.pivot.commons.exceptions.IllegalValueException;
import seedu.pivot.commons.util.JsonUtil;
import seedu.pivot.model.Pivot;
import seedu.pivot.testutil.TypicalCases;

public class JsonSerializablePivotTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializablePivotTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsPivot.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonPivot.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonPivot.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializablePivot dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializablePivot.class).get();
        Pivot pivotFromFile = dataFromFile.toModelType();
        Pivot typicalPersonsPivot = TypicalCases.getTypicalPivot();
        assertEquals(pivotFromFile, typicalPersonsPivot);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializablePivot dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializablePivot.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializablePivot dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializablePivot.class).get();
        assertThrows(IllegalValueException.class, JsonSerializablePivot.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
