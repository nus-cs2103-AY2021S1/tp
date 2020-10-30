package seedu.taskmaster.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.taskmaster.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.taskmaster.commons.exceptions.IllegalValueException;
import seedu.taskmaster.commons.util.JsonUtil;
import seedu.taskmaster.model.session.SessionList;
import seedu.taskmaster.testutil.TypicalStudents;

public class JsonSerializableSessionListTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableSessionListTest");
    private static final Path TYPICAL_SESSION_LIST_FILE = TEST_DATA_FOLDER.resolve("typicalSessionList.json");
    private static final Path INVALID_SESSION_LIST_FILE = TEST_DATA_FOLDER.resolve("invalidSessionList.json");
    private static final Path DUPLICATE_SESSION_FILE = TEST_DATA_FOLDER.resolve("duplicateSessionNameList.json");

    @Test
    public void toModelType_typicalSessionListFile_success() throws Exception {
        JsonSerializableSessionList dataFromFile = JsonUtil.readJsonFile(TYPICAL_SESSION_LIST_FILE,
                JsonSerializableSessionList.class).get();
        SessionList sessionListFromFile = dataFromFile.toModelType();

        SessionList typicalSessionList = TypicalStudents.getTypicalSessionList();
        assertEquals(sessionListFromFile, typicalSessionList);
    }

    @Test
    public void toModelType_invalidSessionListFile_throwsIllegalValueException() throws Exception {
        JsonSerializableSessionList dataFromFile = JsonUtil.readJsonFile(INVALID_SESSION_LIST_FILE,
                JsonSerializableSessionList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateSessionName_throwsIllegalValueException() throws Exception {
        JsonSerializableSessionList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_SESSION_FILE,
                JsonSerializableSessionList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableSessionList.MESSAGE_DUPLICATE_SESSION,
                dataFromFile::toModelType);
    }
}
