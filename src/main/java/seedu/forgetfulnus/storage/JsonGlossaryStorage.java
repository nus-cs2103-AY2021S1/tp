package seedu.forgetfulnus.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.forgetfulnus.commons.core.LogsCenter;
import seedu.forgetfulnus.commons.exceptions.DataConversionException;
import seedu.forgetfulnus.commons.exceptions.IllegalValueException;
import seedu.forgetfulnus.commons.util.FileUtil;
import seedu.forgetfulnus.commons.util.JsonUtil;
import seedu.forgetfulnus.model.ReadOnlyGlossary;
import seedu.forgetfulnus.storage.interfaces.ObjectStorage;

/**
 * A class to access Glossary data stored as a json file on the hard disk.
 */
public class JsonGlossaryStorage implements ObjectStorage<ReadOnlyGlossary> {

    private final Logger logger = LogsCenter.getLogger(this.getClass());

    private Path filePath;

    public JsonGlossaryStorage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the file path of the data file.
     */
    @Override
    public Path getFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyGlossary> readFile() throws DataConversionException {
        return readFile(filePath);
    }
    /**
     * Similar to {@link #readFile()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    @Override
    public Optional<ReadOnlyGlossary> readFile(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableGlossary> jsonGlossary = JsonUtil.readJsonFile(
                filePath, JsonSerializableGlossary.class);
        if (jsonGlossary.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonGlossary.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveFile(ReadOnlyGlossary file) throws IOException {
        saveFile(file, filePath);
    }

    /**
     * Similar to {@link #saveFile(ReadOnlyGlossary)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    @Override
    public void saveFile(ReadOnlyGlossary file, Path filePath) throws IOException {
        requireNonNull(file);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableGlossary(file), filePath);
    }
}
