package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ItineraryList;
import seedu.address.model.ReadOnlyItineraryList;

/**
 * Represents a storage for {@link ItineraryList}.
 */
public interface ItineraryListStorage {

    /**
     * Returns the file path of the itinerary list data file.
     */
    Path getItineraryListFilePath();

    /**
     * Returns ItineraryList data as a {@link ReadOnlyItineraryList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyItineraryList> readItineraryList() throws DataConversionException, IOException;

    /**
     * @see #getItineraryListFilePath()
     */
    Optional<ReadOnlyItineraryList> readItineraryList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyItineraryList} to the storage.
     * @param itineraryList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveItineraryList(ReadOnlyItineraryList itineraryList) throws IOException;

    /**
     * @see #saveItineraryList(ReadOnlyItineraryList)
     */
    void saveItineraryList(ReadOnlyItineraryList itineraryList, Path filePath) throws IOException;

}
