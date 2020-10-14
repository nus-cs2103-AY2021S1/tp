package quickcache.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import quickcache.commons.exceptions.DataConversionException;
import quickcache.model.QuickCache;
import quickcache.model.ReadOnlyQuickCache;

/**
 * Represents a storage for {@link QuickCache}.
 */
public interface QuickCacheStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getQuickCacheFilePath();

    /**
     * Returns QuickCache data as a {@link ReadOnlyQuickCache}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyQuickCache> readQuickCache() throws DataConversionException, IOException;

    /**
     * @see #getQuickCacheFilePath()
     */
    Optional<ReadOnlyQuickCache> readQuickCache(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyQuickCache} to the storage.
     *
     * @param quickCache cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveQuickCache(ReadOnlyQuickCache quickCache) throws IOException;

    /**
     * @see #saveQuickCache(ReadOnlyQuickCache)
     */
    void saveQuickCache(ReadOnlyQuickCache quickCache, Path filePath) throws IOException;

}
