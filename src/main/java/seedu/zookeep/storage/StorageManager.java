package seedu.zookeep.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.zookeep.commons.core.LogsCenter;
import seedu.zookeep.commons.exceptions.DataConversionException;
import seedu.zookeep.model.ReadOnlyUserPrefs;
import seedu.zookeep.model.ReadOnlyZooKeepBook;
import seedu.zookeep.model.UserPrefs;

/**
 * Manages storage of ZooKeepBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ZooKeepBookStorage zooKeepBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ZooKeepBook} and {@code UserPrefStorage}.
     */
    public StorageManager(ZooKeepBookStorage zooKeepBookStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.zooKeepBookStorage = zooKeepBookStorage;
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


    // ================ ZooKeepBook methods ==============================

    @Override
    public Path getZooKeepBookFilePath() {
        return zooKeepBookStorage.getZooKeepBookFilePath();
    }

    @Override
    public Optional<ReadOnlyZooKeepBook> readZooKeepBook() throws DataConversionException, IOException {
        return readZooKeepBook(zooKeepBookStorage.getZooKeepBookFilePath());
    }

    @Override
    public Optional<ReadOnlyZooKeepBook> readZooKeepBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return zooKeepBookStorage.readZooKeepBook(filePath);
    }

    @Override
    public void saveZooKeepBook(ReadOnlyZooKeepBook zooKeepBook) throws IOException {
        saveZooKeepBook(zooKeepBook, zooKeepBookStorage.getZooKeepBookFilePath());
    }

    @Override
    public void saveZooKeepBook(ReadOnlyZooKeepBook zooKeepBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        zooKeepBookStorage.saveZooKeepBook(zooKeepBook, filePath);
    }

}
