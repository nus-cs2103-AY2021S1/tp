package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyMyFitnessBuddy;

/**
 * A class to access My Fitness Buddy data stored as a json file on the hard disk.
 */
public class JsonMyFitnessBuddyStorage implements MyFitnessBuddyStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonMyFitnessBuddyStorage.class);
    private Path filePath;

    public JsonMyFitnessBuddyStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getMyFitnessBuddyFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyMyFitnessBuddy> readFitnessBuddy() throws DataConversionException {
        return readFitnessBuddy(filePath);
    }

    /**
     * Similar to {@link #readFitnessBuddy()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyMyFitnessBuddy> readFitnessBuddy(Path filePath) throws DataConversionException {
        requireNonNull(filePath);
        Optional<JsonSerializableMyFitnessBuddy> jsonFitnessBuddy = JsonUtil.readJsonFile(
                filePath, JsonSerializableMyFitnessBuddy.class);
        if (!jsonFitnessBuddy.isPresent()) {
            return Optional.empty();
        }
        try {
            return Optional.of(jsonFitnessBuddy.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveFitnessBuddy(ReadOnlyMyFitnessBuddy addressBook) throws IOException {
        saveFitnessBuddy(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveFitnessBuddy(ReadOnlyMyFitnessBuddy)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveFitnessBuddy(ReadOnlyMyFitnessBuddy myFitnessBuddy, Path filePath) throws IOException {
        requireNonNull(myFitnessBuddy);
        requireNonNull(filePath);
        FileUtil.createIfMissing(filePath);
        assert FileUtil.isFileExists(filePath) : "Error creating new file";
        JsonUtil.saveJsonFile(new JsonSerializableMyFitnessBuddy(myFitnessBuddy), filePath);
        logger.info("Save completed");
    }

}
