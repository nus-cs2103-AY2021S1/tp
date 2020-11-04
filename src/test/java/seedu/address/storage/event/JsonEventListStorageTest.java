package seedu.address.storage.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.EventList;
import seedu.address.model.ReadOnlyEventList;
import seedu.address.model.event.Event;
import seedu.address.storage.JsonEventListStorage;
import seedu.address.testutil.event.EventUtil;

public class JsonEventListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "Event", "JsonEventListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readEventList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readEventList(null));
    }

    private java.util.Optional<ReadOnlyEventList> readEventList(String filePath) throws Exception {
        return new JsonEventListStorage(Paths.get(filePath)).readEventList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readEventList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readEventList("notJsonFormatEventList.json"));
    }

    @Test
    public void readEventList_invalidEventList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readEventList("invalidEventList.json"));
    }

    @Test
    public void readEventList_invalidAndValidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readEventList("invalidAndValidEventList.json"));
    }

    @Test
    public void readAndSaveEventList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempEventList.json");
        EventList original = new EventList();
        original.addEvent(EventUtil.VALID_EVENT);
        JsonEventListStorage jsonEventListStorage = new JsonEventListStorage(filePath);

        // Save in new file and read back
        jsonEventListStorage.saveEventList(original, filePath);
        ReadOnlyEventList readBack = jsonEventListStorage.readEventList(filePath).get();
        assertEquals(original, new EventList(readBack));

        // Modify data, overwrite exiting file, and read back
        Event event = new Event(EventUtil.makeEventName("CS2100 homework"), EventUtil.makeEventTime("2-2-2002 1200"));
        original.addEvent(event);
        original.removeEvent(EventUtil.VALID_EVENT);
        jsonEventListStorage.saveEventList(original, filePath);
        readBack = jsonEventListStorage.readEventList(filePath).get();
        assertEquals(original, new EventList(readBack));

        // Save and read without specifying file path
        Event event2 = new Event(EventUtil.makeEventName("CS2100 homework"), EventUtil.makeEventTime("3-2-2002 1200"));
        original.addEvent(event2);
        jsonEventListStorage.saveEventList(original); // file path not specified
        readBack = jsonEventListStorage.readEventList().get(); // file path not specified
        assertEquals(original, new EventList(readBack));
    }

    @Test
    public void saveEventList_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveEventList(null, "SomeFile.json"));
    }

    /**
     * Saves {@code eventList} at the specified {@code filePath}.
     */
    private void saveEventList(ReadOnlyEventList eventList, String filePath) {
        try {
            new JsonEventListStorage(Paths.get(filePath))
                    .saveEventList(eventList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveEventList(new EventList(), null));
    }
}
