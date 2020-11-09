package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAttractionList;
import seedu.address.model.ReadOnlyItineraryList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of TrackPad data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AttractionListStorage attractionListStorage;
    private ItineraryListStorage itineraryListStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AttractionListStorage}, {@code ItineraryListStorage} and
     * {@code UserPrefStorage}.
     */
    public StorageManager(AttractionListStorage attractionListStorage, ItineraryListStorage itineraryListStorage,
                          UserPrefsStorage userPrefsStorage) {
        super();
        this.attractionListStorage = attractionListStorage;
        this.itineraryListStorage = itineraryListStorage;
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


    // ================ AttractionList methods ==============================

    @Override
    public Path getAttractionListFilePath() {
        return attractionListStorage.getAttractionListFilePath();
    }

    @Override
    public Optional<ReadOnlyAttractionList> readAttractionList() throws DataConversionException, IOException {
        return readAttractionList(attractionListStorage.getAttractionListFilePath());
    }

    @Override
    public Optional<ReadOnlyAttractionList> readAttractionList(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return attractionListStorage.readAttractionList(filePath);
    }

    @Override
    public void saveAttractionList(ReadOnlyAttractionList attractionList) throws IOException {
        saveAttractionList(attractionList, attractionListStorage.getAttractionListFilePath());
    }

    @Override
    public void saveAttractionList(ReadOnlyAttractionList attractionList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        attractionListStorage.saveAttractionList(attractionList, filePath);
    }

    // ================ ItineraryList methods ==============================

    public Path getItineraryListFilePath() {
        return itineraryListStorage.getItineraryListFilePath();
    }

    public Optional<ReadOnlyItineraryList> readItineraryList() throws DataConversionException, IOException {
        return readItineraryList(itineraryListStorage.getItineraryListFilePath());
    }

    @Override
    public Optional<ReadOnlyItineraryList> readItineraryList(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return itineraryListStorage.readItineraryList(filePath);
    }

    @Override
    public void saveItineraryList(ReadOnlyItineraryList itineraryList) throws IOException {
        saveItineraryList(itineraryList, itineraryListStorage.getItineraryListFilePath());
    }

    @Override
    public void saveItineraryList(ReadOnlyItineraryList itineraryList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        itineraryListStorage.saveItineraryList(itineraryList, filePath);
    }

}
