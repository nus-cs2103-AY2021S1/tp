package seedu.address.storage.deliverable;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.deliverable.ReadOnlyDeliverableBook;
import seedu.address.storage.UserPrefsStorage;

/**
 * Manages storage of DeliverableBook data in local storage.
 */
public class StorageDeliverableManager implements StorageDeliverable {

    private static final Logger logger = LogsCenter.getLogger(StorageDeliverableManager.class);
    private DeliverableBookStorage deliverableBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageDeliverableManager} with the given {@code DeliverableBookStorage}
     * and {@code UserPrefsStorage}.
     */
    public StorageDeliverableManager(DeliverableBookStorage deliverableBookStorage, UserPrefsStorage userPrefsStorage) {
        this.deliverableBookStorage = deliverableBookStorage;
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

    // ================ DeliverableBook methods ==============================

    @Override
    public Path getDeliverableBookFilePath() {
        return deliverableBookStorage.getDeliverableBookFilePath();
    }

    @Override
    public Optional<ReadOnlyDeliverableBook> readDeliverableBook() throws DataConversionException, IOException {
        return readDeliverableBook(deliverableBookStorage.getDeliverableBookFilePath());
    }

    @Override
    public Optional<ReadOnlyDeliverableBook> readDeliverableBook(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from deliverable file: " + filePath);
        return deliverableBookStorage.readDeliverableBook(filePath);
    }

    @Override
    public void saveDeliverableBook(ReadOnlyDeliverableBook deliverableBook) throws IOException {
        saveDeliverableBook(deliverableBook, deliverableBookStorage.getDeliverableBookFilePath());
    }

    @Override
    public void saveDeliverableBook(ReadOnlyDeliverableBook deliverableBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data deliverable file: " + filePath);
        deliverableBookStorage.saveDeliverableBook(deliverableBook, filePath);
    }
}
