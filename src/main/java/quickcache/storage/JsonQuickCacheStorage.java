package quickcache.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import quickcache.commons.core.LogsCenter;
import quickcache.commons.exceptions.DataConversionException;
import quickcache.commons.exceptions.IllegalValueException;
import quickcache.commons.util.FileUtil;
import quickcache.commons.util.JsonUtil;
import quickcache.model.ReadOnlyQuickCache;

/**
 * A class to access QuickCache data stored as a json file on the hard disk.
 */

public class JsonQuickCacheStorage implements QuickCacheStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonQuickCacheStorage.class);

    private final Path filePath;

    public JsonQuickCacheStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getQuickCacheFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyQuickCache> readQuickCache() throws DataConversionException {
        return readQuickCache(filePath);
    }

    /**
     * Similar to {@link #readQuickCache()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyQuickCache> readQuickCache(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableQuickCache> jsonQuickCache = JsonUtil.readJsonFile(
                filePath, JsonSerializableQuickCache.class);
        if (!jsonQuickCache.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonQuickCache.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveQuickCache(ReadOnlyQuickCache quickCache) throws IOException {
        saveQuickCache(quickCache, filePath);
    }

    /**
     * Similar to {@link #saveQuickCache(ReadOnlyQuickCache)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveQuickCache(ReadOnlyQuickCache quickCache, Path filePath) throws IOException {
        requireNonNull(quickCache);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableQuickCache(quickCache), filePath);
    }

}
