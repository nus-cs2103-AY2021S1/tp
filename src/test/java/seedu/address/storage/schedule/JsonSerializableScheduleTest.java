package seedu.address.storage.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.event.Scheduler;
import seedu.address.testutil.TypicalEvents;

public class JsonSerializableScheduleTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableScheduleTest");
    private static final Path TYPICAL_EVENT_FILE = TEST_DATA_FOLDER.resolve("typicalEvents.json");
    private static final Path INVALID_EVENT_FILE = TEST_DATA_FOLDER.resolve("invalidEvents.json");
    private static final Path DUPLICATE_EVENT_FILE = TEST_DATA_FOLDER.resolve("duplicateEvents.json");

    @Test
    public void toModelType_typicalEventsFile_success() throws Exception {
        JsonSerializableSchedule dataFromFile = JsonUtil.readJsonFile(TYPICAL_EVENT_FILE,
                JsonSerializableSchedule.class).get();
        Scheduler scheduleFromFile = dataFromFile.toModelType();
        Scheduler typicalEventsSchedule = TypicalEvents.getTypicalScheduler();
        assertEquals(scheduleFromFile, typicalEventsSchedule);
    }

    @Test
    public void toModelType_invalidEventsFile_throwsIllegalValueException() throws Exception {
        JsonSerializableSchedule dataFromFile = JsonUtil.readJsonFile(INVALID_EVENT_FILE,
                JsonSerializableSchedule.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateEvents_throwsIllegalValueException() throws Exception {
        JsonSerializableSchedule dataFromFile = JsonUtil.readJsonFile(DUPLICATE_EVENT_FILE,
                JsonSerializableSchedule.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableSchedule.MESSAGE_DUPLICATE_EVENTS,
                dataFromFile::toModelType);
    }

}
