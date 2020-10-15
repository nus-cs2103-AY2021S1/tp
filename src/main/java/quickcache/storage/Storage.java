package quickcache.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import quickcache.commons.exceptions.DataConversionException;
import quickcache.model.ReadOnlyQuickCache;
import quickcache.model.ReadOnlyUserPrefs;
import quickcache.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends QuickCacheStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getQuickCacheFilePath();

    @Override
    Optional<ReadOnlyQuickCache> readQuickCache() throws DataConversionException, IOException;

    @Override
    void saveQuickCache(ReadOnlyQuickCache quickCache) throws IOException;

}
