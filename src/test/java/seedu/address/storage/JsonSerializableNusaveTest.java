package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.Nusave;
import seedu.address.testutil.TypicalBudgets;

class JsonSerializableNusaveTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableNusaveTest");
    private static final Path TYPICAL_BUDGETS_FILE = TEST_DATA_FOLDER.resolve("typicalBudgetsNusave.json");
    private static final Path INVALID_BUDGETS_FILE = TEST_DATA_FOLDER.resolve("invalidBudgetNusave.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableNusave dataFromFile = JsonUtil.readJsonFile(TYPICAL_BUDGETS_FILE,
                JsonSerializableNusave.class).get();
        Nusave nusaveFromFile = dataFromFile.toModelType();
        Nusave typicalBudgetsNusave = TypicalBudgets.getTypicalNusave();
        // assertEquals(typicalBudgetsNusave, nusaveFromFile);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableNusave dataFromFile = JsonUtil.readJsonFile(INVALID_BUDGETS_FILE,
                JsonSerializableNusave.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

}
