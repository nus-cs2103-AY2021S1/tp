package ay2021s1_cs2103_w16_3.finesse.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import ay2021s1_cs2103_w16_3.finesse.commons.core.LogsCenter;
import ay2021s1_cs2103_w16_3.finesse.commons.exceptions.DataConversionException;
import ay2021s1_cs2103_w16_3.finesse.commons.exceptions.IllegalValueException;
import ay2021s1_cs2103_w16_3.finesse.commons.util.FileUtil;
import ay2021s1_cs2103_w16_3.finesse.commons.util.JsonUtil;
import ay2021s1_cs2103_w16_3.finesse.model.ReadOnlyFinanceTracker;

/**
 * A class to access FinanceTracker data stored as a json file on the hard disk.
 */
public class JsonFinanceTrackerStorage implements FinanceTrackerStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonFinanceTrackerStorage.class);

    private Path filePath;

    public JsonFinanceTrackerStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getFinanceTrackerFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyFinanceTracker> readFinanceTracker() throws DataConversionException {
        return readFinanceTracker(filePath);
    }

    /**
     * Similar to {@link #readFinanceTracker()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyFinanceTracker> readFinanceTracker(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableFinanceTracker> jsonFinanceTracker = JsonUtil.readJsonFile(
                filePath, JsonSerializableFinanceTracker.class);
        if (!jsonFinanceTracker.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonFinanceTracker.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveFinanceTracker(ReadOnlyFinanceTracker financeTracker) throws IOException {
        saveFinanceTracker(financeTracker, filePath);
    }

    /**
     * Similar to {@link #saveFinanceTracker(ReadOnlyFinanceTracker)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveFinanceTracker(ReadOnlyFinanceTracker financeTracker, Path filePath) throws IOException {
        requireNonNull(financeTracker);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableFinanceTracker(financeTracker), filePath);
    }

}
