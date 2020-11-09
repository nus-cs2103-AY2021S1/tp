package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTravelPlanner;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of TravelPlanner data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private TravelPlannerStorage travelPlannerStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code TravelPlannerStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(TravelPlannerStorage travelPlannerStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.travelPlannerStorage = travelPlannerStorage;
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

    // ================ Wanderlust methods ==============================

    @Override
    public Path getTravelPlannerFilePath() {
        return travelPlannerStorage.getTravelPlannerFilePath();
    }

    @Override
    public Optional<ReadOnlyTravelPlanner> readTravelPlanner() throws DataConversionException, IOException {
        return readTravelPlanner(travelPlannerStorage.getTravelPlannerFilePath());
    }

    @Override
    public Optional<ReadOnlyTravelPlanner> readTravelPlanner(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return travelPlannerStorage.readTravelPlanner(filePath);
    }

    @Override
    public void saveTravelPlanner(ReadOnlyTravelPlanner travelPlanner) throws IOException {
        saveTravelPlanner(travelPlanner, travelPlannerStorage.getTravelPlannerFilePath());
    }

    @Override
    public void saveTravelPlanner(ReadOnlyTravelPlanner travelPlanner, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        travelPlannerStorage.saveTravelPlanner(travelPlanner, filePath);
    }
}
