package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyFlashcardBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private FlashcardBookStorage flashcardBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code FlashcardBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(FlashcardBookStorage flashcardBookStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.flashcardBookStorage = flashcardBookStorage;
        this.userPrefsStorage = userPrefsStorage;
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


    // ================ AddressBook methods ==============================

    @Override
    public Path getFlashcardBookFilePath() {
        return flashcardBookStorage.getFlashcardBookFilePath();
    }

    @Override
    public Optional<ReadOnlyFlashcardBook> readFlashcardBook() throws DataConversionException, IOException {
        return readFlashcardBook(flashcardBookStorage.getFlashcardBookFilePath());
    }

    @Override
    public Optional<ReadOnlyFlashcardBook> readFlashcardBook(Path filePath)
        throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return flashcardBookStorage.readFlashcardBook(filePath);
    }

    @Override
    public void saveFlashcardBook(ReadOnlyFlashcardBook addressBook) throws IOException {
        saveFlashcardBook(addressBook, flashcardBookStorage.getFlashcardBookFilePath());
    }

    @Override
    public void saveFlashcardBook(ReadOnlyFlashcardBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        flashcardBookStorage.saveFlashcardBook(addressBook, filePath);
    }

}
