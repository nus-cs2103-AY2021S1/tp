package seedu.resireg.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.resireg.commons.core.LogsCenter;
import seedu.resireg.commons.exceptions.DataConversionException;
import seedu.resireg.commons.exceptions.IllegalValueException;
import seedu.resireg.commons.util.FileUtil;
import seedu.resireg.commons.util.JsonUtil;
import seedu.resireg.model.ReadOnlyResiReg;

/**
 * A class to access ResiReg data stored as a json file on the hard disk.
 */
public class JsonResiRegStorage implements ResiRegStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonResiRegStorage.class);
    private static final String ARCHIVE_FILENAME = "archive.json";

    private Path filePath;

    public JsonResiRegStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getResiRegFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyResiReg> readResiReg() throws DataConversionException {
        return readResiReg(filePath);
    }

    /**
     * Similar to {@link #readResiReg()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyResiReg> readResiReg(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableResiReg> jsonResiReg = JsonUtil.readJsonFile(
                filePath, JsonSerializableResiReg.class);
        if (!jsonResiReg.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonResiReg.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveResiReg(ReadOnlyResiReg resiReg) throws IOException {
        saveResiReg(resiReg, filePath);
    }

    /**
     * Similar to {@link #saveResiReg(ReadOnlyResiReg)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveResiReg(ReadOnlyResiReg resiReg, Path filePath) throws IOException {
        requireNonNull(resiReg);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableResiReg(resiReg), filePath);
    }

    @Override
    public void archiveResiReg(ReadOnlyResiReg resiReg) throws IOException {
        Path currentPath = filePath.subpath(0, filePath.getNameCount() - 1).toAbsolutePath();
        Path newPath = Paths.get(currentPath.toString(), resiReg.getSemesterString(), ARCHIVE_FILENAME);

        saveResiReg(resiReg, newPath);
    }

}
