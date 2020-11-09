package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyBookingBook;
import seedu.address.model.ReadOnlyPersonBook;


/**
 * Represents a storage for {@link seedu.address.model.BookingBook}.
 */
public interface BookingBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getBookingBookFilePath();

    /**
     * Returns BookingBook data as a {@link ReadOnlyPersonBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyBookingBook> readBookingBook() throws DataConversionException, IOException;

    /**
     * @see #getBookingBookFilePath()
     */
    Optional<ReadOnlyBookingBook> readBookingBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyBookingBook} to the storage.
     * @param bookingBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveBookingBook(ReadOnlyBookingBook bookingBook) throws IOException;

    /**
     * @see #saveBookingBook(ReadOnlyBookingBook)
     */
    void saveBookingBook(ReadOnlyBookingBook bookingBook, Path filePath) throws IOException;

}
