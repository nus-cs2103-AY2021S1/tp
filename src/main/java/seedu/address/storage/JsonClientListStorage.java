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
import seedu.address.model.ReadOnlyClientList;

/**
 * A class to access ClientList data stored as a json file on the hard disk.
 */
public class JsonClientListStorage implements ClientListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonClientListStorage.class);

    private Path filePath;

    public JsonClientListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getClientListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyClientList> readClientList() throws DataConversionException {
        return readClientList(filePath);
    }

    /**
     * Similar to {@link #readClientList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyClientList> readClientList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableClientList> jsonClientList = JsonUtil.readJsonFile(
                filePath, JsonSerializableClientList.class);
        if (!jsonClientList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonClientList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveClientList(ReadOnlyClientList clientList) throws IOException {
        saveClientList(clientList, filePath);
    }

    /**
     * Similar to {@link #saveClientList(ReadOnlyClientList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveClientList(ReadOnlyClientList clientList, Path filePath) throws IOException {
        requireNonNull(clientList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableClientList(clientList), filePath);
    }

}
