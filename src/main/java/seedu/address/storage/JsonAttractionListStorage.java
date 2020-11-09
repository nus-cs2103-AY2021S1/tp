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
import seedu.address.model.ReadOnlyAttractionList;

/**
 * A class to access AttractionList data stored as a json file on the hard disk.
 */
public class JsonAttractionListStorage implements AttractionListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAttractionListStorage.class);

    private Path filePath;

    public JsonAttractionListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAttractionListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyAttractionList> readAttractionList() throws DataConversionException {
        return readAttractionList(filePath);
    }

    /**
     * Similar to {@link #readAttractionList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyAttractionList> readAttractionList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableAttractionList> jsonAttractionList = JsonUtil.readJsonFile(
                filePath, JsonSerializableAttractionList.class);
        if (!jsonAttractionList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAttractionList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveAttractionList(ReadOnlyAttractionList attractionList) throws IOException {
        saveAttractionList(attractionList, filePath);
    }

    /**
     * Similar to {@link #saveAttractionList(ReadOnlyAttractionList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAttractionList(ReadOnlyAttractionList attractionList, Path filePath) throws IOException {
        requireNonNull(attractionList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableAttractionList(attractionList), filePath);
    }

}
