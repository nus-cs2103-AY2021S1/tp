package trackitnus.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import trackitnus.commons.core.LogsCenter;
import trackitnus.commons.exceptions.DataConversionException;
import trackitnus.commons.exceptions.IllegalValueException;
import trackitnus.commons.util.FileUtil;
import trackitnus.commons.util.JsonUtil;
import trackitnus.model.ReadOnlyTrackIter;

/**
 * A class to access TrackIter data stored as a json file on the hard disk.
 */
public class JsonTrackIterStorage implements TrackIterStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTrackIterStorage.class);

    private final Path filePath;

    public JsonTrackIterStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTrackIterFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTrackIter> readTrackIter() throws DataConversionException {
        return readTrackIter(filePath);
    }

    /**
     * Similar to {@link #readTrackIter()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyTrackIter> readTrackIter(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTrackIter> jsonTrackIter = JsonUtil.readJsonFile(
            filePath, JsonSerializableTrackIter.class);
        if (!jsonTrackIter.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTrackIter.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTrackIter(ReadOnlyTrackIter trackIter) throws IOException {
        saveTrackIter(trackIter, filePath);
    }

    /**
     * Similar to {@link #saveTrackIter(ReadOnlyTrackIter)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTrackIter(ReadOnlyTrackIter trackIter, Path filePath) throws IOException {
        requireNonNull(trackIter);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTrackIter(trackIter), filePath);
    }

}
