package chopchop.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import chopchop.commons.exceptions.DataConversionException;

public interface UsageStorage <T, U> {
    /**
     * Returns the file path of the data file.
     */
    Path getUsageFilePath();

    Optional<List<U>> readUsages() throws DataConversionException;

    Optional<List<U>> readUsages(Path filePath) throws DataConversionException;

    void saveUsages(List<T> usages) throws IOException;

    void saveUsages(List<T> usages, Path filePath) throws IOException;
}
