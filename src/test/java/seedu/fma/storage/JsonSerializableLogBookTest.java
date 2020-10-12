package seedu.fma.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.fma.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.fma.commons.exceptions.IllegalValueException;
import seedu.fma.commons.util.JsonUtil;
import seedu.fma.model.LogBook;
import seedu.fma.testutil.TypicalLogs;

public class JsonSerializableLogBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableLogBookTest");
    private static final Path TYPICAL_LOG_FILE = TEST_DATA_FOLDER.resolve("typicalLogBook.json");
    private static final Path INVALID_LOG_FILE = TEST_DATA_FOLDER.resolve("invalidEntryLogBook.json");
    private static final Path DUPLICATE_LOG_FILE = TEST_DATA_FOLDER.resolve("duplicateEntryLogBook.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableLogBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_LOG_FILE,
                JsonSerializableLogBook.class).get();
        LogBook logBookFromFile = dataFromFile.toModelType();
        LogBook typicalPersonsLogBook = TypicalLogs.getTypicalLogBook();
        assertEquals(logBookFromFile, typicalPersonsLogBook);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableLogBook dataFromFile = JsonUtil.readJsonFile(INVALID_LOG_FILE,
                JsonSerializableLogBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableLogBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_LOG_FILE,
                JsonSerializableLogBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableLogBook.MESSAGE_DUPLICATE_LOG,
                dataFromFile::toModelType);
    }

}
