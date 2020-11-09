package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyBookingBook;
import seedu.address.model.ReadOnlyPersonBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends PersonBookStorage, BookingBookStorage, UserPrefsStorage, RoomServiceBookStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getPersonBookFilePath();

    @Override
    Optional<ReadOnlyPersonBook> readPersonBook() throws DataConversionException, IOException;

    @Override
    void savePersonBook(ReadOnlyPersonBook personBook) throws IOException;

    @Override
    Path getBookingBookFilePath();

    @Override
    Optional<ReadOnlyBookingBook> readBookingBook() throws DataConversionException, IOException;

    @Override
    void saveBookingBook(ReadOnlyBookingBook bookingBook) throws IOException;

}
