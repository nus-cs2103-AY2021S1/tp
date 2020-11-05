package seedu.address.storage.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.EventList;
import seedu.address.storage.JsonSerializableEventList;
import seedu.address.testutil.event.EventUtil;

public class JsonSerializableEventListTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "Event",
            "JsonSerializableEventListTest");
    private static final Path TYPICAL_EVENT_FILE = TEST_DATA_FOLDER.resolve("typicalEventList.json");
    private static final Path INVALID_EVENT_FILE = TEST_DATA_FOLDER.resolve("invalidEventList.json");
    private static final Path DUPLICATE_EVENT_FILE = TEST_DATA_FOLDER.resolve("duplicateEventList.json");
    private static EventList typicalEventList = new EventList();

    @Test
    public void toModelType_typicalEventsFile_success() throws Exception {
        JsonSerializableEventList dataFromFile = JsonUtil.readJsonFile(TYPICAL_EVENT_FILE,
                JsonSerializableEventList.class).get();
        EventList eventListFromFile = dataFromFile.toModelType();
        typicalEventList.addEvent(EventUtil.VALID_EVENT);
        EventList typicalPersonsModuleList = typicalEventList;
        assertEquals(eventListFromFile, typicalPersonsModuleList);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableEventList dataFromFile = JsonUtil.readJsonFile(INVALID_EVENT_FILE,
                JsonSerializableEventList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableEventList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_EVENT_FILE,
                JsonSerializableEventList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableEventList.MESSAGE_DUPLICATE_EVENT,
                dataFromFile::toModelType);
    }
}
