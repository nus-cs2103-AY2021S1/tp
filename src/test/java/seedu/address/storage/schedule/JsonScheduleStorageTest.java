package seedu.address.storage.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.ALICE_CLASS_EVENT;
import static seedu.address.testutil.TypicalEvents.ALSO_NOT_TYPICAL_EVENT;
import static seedu.address.testutil.TypicalEvents.NOT_TYPICAL_EVENT;
import static seedu.address.testutil.TypicalEvents.getTypicalScheduler;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.event.ReadOnlyEvent;
import seedu.address.model.event.Scheduler;

public class JsonScheduleStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonScheduleStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readSchedule_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readSchedule(null));
    }

    private java.util.Optional<ReadOnlyEvent> readSchedule(String filePath) throws Exception {
        return new JsonScheduleStorage(Paths.get(filePath)).readSchedule(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readSchedule("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readSchedule("notJsonFormat.json"));
    }

    @Test
    public void readSchedule_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSchedule("invalidEvent.json"));
    }

    @Test
    public void readSchedule_invalidAndValidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSchedule("invalidAndValidEvent.json"));
    }

    @Test
    public void readAndsaveSchedule_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");

        Scheduler original = getTypicalScheduler();
        JsonScheduleStorage jsonScheduleStorage = new JsonScheduleStorage(filePath);

        // Save in new file and read back
        jsonScheduleStorage.saveSchedule(original, filePath);
        ReadOnlyEvent readBack = jsonScheduleStorage.readSchedule(filePath).get();
        assertEquals(original, new Scheduler(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addEvent(NOT_TYPICAL_EVENT);
        original.removeEvent(ALICE_CLASS_EVENT);
        jsonScheduleStorage.saveSchedule(original, filePath);
        readBack = jsonScheduleStorage.readSchedule(filePath).get();
        assertEquals(original, new Scheduler(readBack));

        // Save and read without specifying file path
        original.addEvent(ALSO_NOT_TYPICAL_EVENT);
        jsonScheduleStorage.saveSchedule(original); // file path not specified
        readBack = jsonScheduleStorage.readSchedule().get(); // file path not specified
        assertEquals(original, new Scheduler(readBack));

    }

    @Test
    public void saveSchedule_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSchedule(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveSchedule(ReadOnlyEvent addressBook, String filePath) {
        try {
            new JsonScheduleStorage(Paths.get(filePath))
                    .saveSchedule(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveSchedule_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSchedule(new Scheduler(), null));
    }
}
