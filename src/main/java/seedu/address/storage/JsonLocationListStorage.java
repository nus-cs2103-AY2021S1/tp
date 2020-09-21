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
import seedu.address.model.ReadOnlyLocationList;

/**
 * A class to access Location data stored as a json file on the hard disk.
 */
public class JsonLocationListStorage implements LocationListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonLocationListStorage.class);

    private Path filePath;

    public JsonLocationListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getLocationListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyLocationList> readLocationList() throws DataConversionException {
        return readLocationList(filePath);
    }

    /**
     * Similar to {@link #readLocationList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyLocationList> readLocationList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableLocationList> jsonLocation = JsonUtil.readJsonFile(
                filePath, JsonSerializableLocationList.class);
        if (jsonLocation.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonLocation.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveLocationList(ReadOnlyLocationList addressBook) throws IOException {
        saveLocationList(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveLocationList(ReadOnlyLocationList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveLocationList(ReadOnlyLocationList addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableLocationList(addressBook), filePath);
    }

}
