package seedu.address.storage;

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
import seedu.address.model.ReadOnlyBookingBook;



/**
 * A class to access BookingBook data stored as a json file on the hard disk.
 */
public class JsonBookingBookStorage implements BookingBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonBookingBookStorage.class);

    private Path filePath;

    public JsonBookingBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getBookingBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyBookingBook> readBookingBook() throws DataConversionException {
        return readBookingBook(filePath);
    }

    /**
     * Similar to {@link #readBookingBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyBookingBook> readBookingBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableBookingBook> jsonBookingBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableBookingBook.class);
        if (!jsonBookingBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonBookingBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveBookingBook(ReadOnlyBookingBook bookingBook) throws IOException {
        saveBookingBook(bookingBook, filePath);
    }

    /**
     * Similar to {@link #saveBookingBook(ReadOnlyBookingBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveBookingBook(ReadOnlyBookingBook bookingBook, Path filePath) throws IOException {
        requireNonNull(bookingBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableBookingBook(bookingBook), filePath);
    }

}
