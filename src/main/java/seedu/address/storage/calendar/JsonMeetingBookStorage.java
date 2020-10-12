package seedu.address.storage.calendar;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyBidBook;
import seedu.address.model.ReadOnlyMeetingManager;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonSerializableAddressBook;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonMeetingBookStorage implements MeetingBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);

    private Path filePath;

    public JsonMeetingBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getMeetingBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyMeetingManager> readMeetingBook() throws DataConversionException, IOException {
        return readMeetingBook(filePath);
    }

    @Override
    public Optional<ReadOnlyMeetingManager> readMeetingBook(Path filePath) throws DataConversionException, IOException {
        requireNonNull(filePath);

        Optional<JsonSerializableAddressBook> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableAddressBook.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveMeetingBook(ReadOnlyMeetingManager meetingManager) throws IOException {

    }

    @Override
    public void saveMeetingManager(ReadOnlyMeetingManager meetingManager, Path filePath) throws IOException {

    }
}
