package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyMeetingBook;

public interface MeetingBookStorage {

    Path getMeetingBookFilePath();

    Optional<ReadOnlyMeetingBook> readMeetingBook() throws DataConversionException, IOException;

    Optional<ReadOnlyMeetingBook> readMeetingBook(Path filePath) throws DataConversionException, IOException;

    void saveMeetingBook(ReadOnlyMeetingBook meetingBook) throws IOException;

    void saveMeetingBook(ReadOnlyMeetingBook meetingBook, Path filePath) throws IOException;

}
