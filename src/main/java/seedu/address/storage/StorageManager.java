package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyBookingBook;
import seedu.address.model.ReadOnlyPersonBook;
import seedu.address.model.ReadOnlyRoomServiceBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of PersonBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private PersonBookStorage personBookStorage;
    private BookingBookStorage bookingBookStorage;
    private UserPrefsStorage userPrefsStorage;
    private RoomServiceBookStorage roomServiceBookStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code PersonBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(PersonBookStorage personBookStorage, BookingBookStorage bookingBookStorage,
                          UserPrefsStorage userPrefsStorage, RoomServiceBookStorage roomServiceBookStorage) {
        super();
        this.personBookStorage = personBookStorage;
        this.bookingBookStorage = bookingBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.roomServiceBookStorage = roomServiceBookStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ PersonBook methods ==============================

    @Override
    public Path getPersonBookFilePath() {
        return personBookStorage.getPersonBookFilePath();
    }

    @Override
    public Optional<ReadOnlyPersonBook> readPersonBook() throws DataConversionException, IOException {
        return readPersonBook(personBookStorage.getPersonBookFilePath());
    }

    @Override
    public Optional<ReadOnlyPersonBook> readPersonBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return personBookStorage.readPersonBook(filePath);
    }

    @Override
    public void savePersonBook(ReadOnlyPersonBook personBook) throws IOException {
        savePersonBook(personBook, personBookStorage.getPersonBookFilePath());
    }

    @Override
    public void savePersonBook(ReadOnlyPersonBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        personBookStorage.savePersonBook(addressBook, filePath);
    }

    // ================ BookingBook methods ==============================

    @Override
    public Path getBookingBookFilePath() {
        return bookingBookStorage.getBookingBookFilePath();
    }

    @Override
    public Optional<ReadOnlyBookingBook> readBookingBook() throws DataConversionException, IOException {
        return readBookingBook(bookingBookStorage.getBookingBookFilePath());
    }

    @Override
    public Optional<ReadOnlyBookingBook> readBookingBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return bookingBookStorage.readBookingBook(filePath);
    }

    @Override
    public void saveBookingBook(ReadOnlyBookingBook bookingBook) throws IOException {
        saveBookingBook(bookingBook, bookingBookStorage.getBookingBookFilePath());
    }

    @Override
    public void saveBookingBook(ReadOnlyBookingBook bookingBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        bookingBookStorage.saveBookingBook(bookingBook, filePath);
    }

    // ================ RoomServiceBook methods ==============================

    @Override
    public Path getRoomServiceBookFilePath() {
        return roomServiceBookStorage.getRoomServiceBookFilePath();
    }

    @Override
    public Optional<ReadOnlyRoomServiceBook> readRoomServiceBook() throws DataConversionException, IOException {
        return readRoomServiceBook(roomServiceBookStorage.getRoomServiceBookFilePath());
    }

    @Override
    public Optional<ReadOnlyRoomServiceBook> readRoomServiceBook(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return roomServiceBookStorage.readRoomServiceBook(filePath);
    }

    @Override
    public void saveRoomServiceBook(ReadOnlyRoomServiceBook roomServiceBook) throws IOException {
        saveRoomServiceBook(roomServiceBook, roomServiceBookStorage.getRoomServiceBookFilePath());
    }

    @Override
    public void saveRoomServiceBook(ReadOnlyRoomServiceBook roomServiceBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        roomServiceBookStorage.saveRoomServiceBook(roomServiceBook, filePath);
    }



}
