package jimmy.mcgymmy.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import jimmy.mcgymmy.commons.exceptions.DataConversionException;
import jimmy.mcgymmy.model.McGymmy;
import jimmy.mcgymmy.model.ReadOnlyMcGymmy;

/**
 * Represents a storage for {@link McGymmy}.
 */
public interface McGymmyStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getMcGymmyFilePath();

    /**
     * Returns McGymmy data as a {@link ReadOnlyMcGymmy}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyMcGymmy> readMcGymmy() throws DataConversionException, IOException;

    /**
     * @see #getMcGymmyFilePath()
     */
    Optional<ReadOnlyMcGymmy> readMcGymmy(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyMcGymmy} to the storage.
     *
     * @param mcGymmy cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveMcGymmy(ReadOnlyMcGymmy mcGymmy) throws IOException;

    /**
     * @see #saveMcGymmy(ReadOnlyMcGymmy)
     */
    void saveMcGymmy(ReadOnlyMcGymmy mcGymmy, Path filePath) throws IOException;

}
