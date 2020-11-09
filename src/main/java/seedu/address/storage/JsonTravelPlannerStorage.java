package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.DuplicateException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyTravelPlanner;

/**
 * A class to access TravelPlanner data stored as a json file on the hard disk.
 */
public class JsonTravelPlannerStorage implements TravelPlannerStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTravelPlannerStorage.class);

    private Path filePath;

    public JsonTravelPlannerStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTravelPlannerFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTravelPlanner> readTravelPlanner() throws DataConversionException {
        return readTravelPlanner(filePath);
    }

    /**
     * Similar to {@link #readTravelPlanner()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyTravelPlanner> readTravelPlanner(Path filePath) throws DataConversionException {
        requireNonNull(filePath);
        assert filePath != null;

        Optional<JsonSerializableTravelPlanner> jsonTravelPlanner = JsonUtil.readJsonFile(
                filePath, JsonSerializableTravelPlanner.class);
        if (!jsonTravelPlanner.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTravelPlanner.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        } catch (DuplicateException dupEx) {
            logger.info("Duplicate objects found in " + filePath + ": \n" + dupEx.getMessage());
            throw new DataConversionException(dupEx);
        }
    }

    @Override
    public void saveTravelPlanner(ReadOnlyTravelPlanner travelPlanner) throws IOException {
        saveTravelPlanner(travelPlanner, filePath);
    }

    /**
     * Similar to {@link #saveTravelPlanner(ReadOnlyTravelPlanner)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTravelPlanner(ReadOnlyTravelPlanner travelPlanner, Path filePath) throws IOException {
        requireNonNull(travelPlanner);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTravelPlanner(travelPlanner), filePath);
    }
}
