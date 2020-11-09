package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTravelPlanner;
import seedu.address.model.TravelPlanner;

/**
 * Represents a storage for {@link TravelPlanner}.
 */
public interface TravelPlannerStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTravelPlannerFilePath();

    /**
     * Returns TravelPlanner data as a {@link ReadOnlyTravelPlanner}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTravelPlanner> readTravelPlanner() throws DataConversionException, IOException;

    /**
     * @see #getTravelPlannerFilePath()
     */
    Optional<ReadOnlyTravelPlanner> readTravelPlanner(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTravelPlanner} to the storage.
     * @param travelPlanner cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTravelPlanner(ReadOnlyTravelPlanner travelPlanner) throws IOException;

    /**
     * @see #saveTravelPlanner(ReadOnlyTravelPlanner)
     */
    void saveTravelPlanner(ReadOnlyTravelPlanner travelPlanner, Path filePath) throws IOException;

}
