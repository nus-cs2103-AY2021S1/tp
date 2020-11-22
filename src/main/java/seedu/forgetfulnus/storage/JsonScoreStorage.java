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
import seedu.forgetfulnus.model.ReadOnlyScoreList;
import seedu.forgetfulnus.storage.interfaces.ObjectStorage;

/**
 * A class to access Score data stored as a json file on the hard disk.
 */
public class JsonScoreStorage implements ObjectStorage<ReadOnlyScoreList> {

    private static final Logger logger = LogsCenter.getLogger(JsonScoreStorage.class);

    private Path filePath;

    public JsonScoreStorage(Path filePath) {
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
    public Optional<ReadOnlyScoreList> readFile() throws DataConversionException, IOException {
        return readFile(filePath);
    }

    @Override
    public Optional<ReadOnlyScoreList> readFile(Path filePath) throws DataConversionException, IOException {
        requireNonNull(filePath);
        Optional<JsonScoreList> jsonScoreList = JsonUtil.readJsonFile(
                filePath, JsonScoreList.class);
        if (jsonScoreList.isEmpty()) {
            return Optional.empty();
        }
        try {
            return Optional.of(jsonScoreList.get().toModelType());
        } catch (IllegalValueException e) {
            logger.info("Illegal values found in " + filePath + ": " + e.getMessage());
            throw new DataConversionException(e);
        }
    }

    @Override
    public void saveFile(ReadOnlyScoreList scoreList) throws IOException {
        saveFile(scoreList, filePath);
    }

    @Override
    public void saveFile(ReadOnlyScoreList scoreList, Path filePath) throws IOException {
        requireNonNull(scoreList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonScoreList(scoreList), filePath);
    }
}
