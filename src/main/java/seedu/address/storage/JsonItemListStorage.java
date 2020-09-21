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
import seedu.address.model.ReadOnlyItemList;
import seedu.address.model.ReadOnlyLocationList;

/**
 * A class to access ItemList data stored as a json file on the hard disk.
 */
public class JsonItemListStorage implements ItemListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonItemListStorage.class);

    private Path filePath;

    public JsonItemListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getItemListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyItemList> readItemList() throws DataConversionException {
        return readItemList(filePath);
    }

    /**
     * Similar to {@link #readItemList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyItemList> readItemList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableItemList> jsonItemList = JsonUtil.readJsonFile(
                filePath, JsonSerializableItemList.class);
        if (jsonItemList.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonItemList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveItemList(ReadOnlyItemList addressBook) throws IOException {
        saveItemList(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveItemList(ReadOnlyItemList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveItemList(ReadOnlyItemList addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableItemList(addressBook), filePath);
    }

    @Override
    public void saveLocationList(ReadOnlyLocationList addressBook) {
    }

    @Override
    public void saveLocationList(ReadOnlyLocationList addressBook, Path filePath) {
    }

}
