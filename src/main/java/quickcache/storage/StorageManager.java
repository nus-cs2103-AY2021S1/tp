package quickcache.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import quickcache.commons.core.LogsCenter;
import quickcache.commons.exceptions.DataConversionException;
import quickcache.model.ReadOnlyQuickCache;
import quickcache.model.ReadOnlyUserPrefs;
import quickcache.model.UserPrefs;

/**
 * Manages storage of QuickCache data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private final QuickCacheStorage quickCacheStorage;
    private final UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code QuickCacheStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(QuickCacheStorage quickCacheStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.quickCacheStorage = quickCacheStorage;
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


    // ================ QuickCache methods ==============================

    @Override
    public Path getQuickCacheFilePath() {
        return quickCacheStorage.getQuickCacheFilePath();
    }

    @Override
    public Optional<ReadOnlyQuickCache> readQuickCache() throws DataConversionException, IOException {
        return readQuickCache(quickCacheStorage.getQuickCacheFilePath());
    }

    @Override
    public Optional<ReadOnlyQuickCache> readQuickCache(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return quickCacheStorage.readQuickCache(filePath);
    }

    @Override
    public void saveQuickCache(ReadOnlyQuickCache quickCache) throws IOException {
        saveQuickCache(quickCache, quickCacheStorage.getQuickCacheFilePath());
    }

    @Override
    public void saveQuickCache(ReadOnlyQuickCache quickCache, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        quickCacheStorage.saveQuickCache(quickCache, filePath);
    }

}
