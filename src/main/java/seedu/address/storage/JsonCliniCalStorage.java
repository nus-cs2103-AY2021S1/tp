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
import seedu.address.model.ReadOnlyCliniCal;

/**
 * A class to access CliniCal data stored as a json file on the hard disk.
 */
public class JsonCliniCalStorage implements CliniCalStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonCliniCalStorage.class);

    private Path filePath;

    public JsonCliniCalStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getCliniCalFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyCliniCal> readCliniCal() throws DataConversionException {
        return readCliniCal(filePath);
    }

    /**
     * Similar to {@link #readCliniCal()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyCliniCal> readCliniCal(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableCliniCal> jsonCliniCal = JsonUtil.readJsonFile(
                filePath, JsonSerializableCliniCal.class);
        if (!jsonCliniCal.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonCliniCal.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveCliniCal(ReadOnlyCliniCal cliniCal) throws IOException {
        saveCliniCal(cliniCal, filePath);
    }

    /**
     * Similar to {@link #saveCliniCal(ReadOnlyCliniCal)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveCliniCal(ReadOnlyCliniCal cliniCal, Path filePath) throws IOException {
        requireNonNull(cliniCal);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableCliniCal(cliniCal), filePath);
    }

}
