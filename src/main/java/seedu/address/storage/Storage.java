package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAttractionList;
import seedu.address.model.ReadOnlyItineraryList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AttractionListStorage, ItineraryListStorage, UserPrefsStorage {

    //=========== User prefs ================================================================================

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    //=========== Attraction List ================================================================================

    @Override
    Path getAttractionListFilePath();

    @Override
    Optional<ReadOnlyAttractionList> readAttractionList() throws DataConversionException, IOException;

    @Override
    void saveAttractionList(ReadOnlyAttractionList attractionList) throws IOException;

    //=========== Itinerary List ================================================================================

    @Override
    Path getItineraryListFilePath();

    @Override
    Optional<ReadOnlyItineraryList> readItineraryList() throws DataConversionException, IOException;

    @Override
    void saveItineraryList(ReadOnlyItineraryList itineraryList) throws IOException;

}
