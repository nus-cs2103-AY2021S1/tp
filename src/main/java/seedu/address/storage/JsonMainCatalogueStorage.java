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
import seedu.address.model.ReadOnlyMainCatalogue;

/**
 * A class to access MainCatalogue data stored as a json file on the hard disk.
 */
public class JsonMainCatalogueStorage implements MainCatalogueStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonMainCatalogueStorage.class);

    private Path filePath;

    public JsonMainCatalogueStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getMainCatalogueFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyMainCatalogue> readMainCatalogue() throws DataConversionException {
        return readMainCatalogue(filePath);
    }

    /**
     * Similar to {@link #readMainCatalogue()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyMainCatalogue> readMainCatalogue(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableMainCatalogue> jsonMainCatalogue = JsonUtil.readJsonFile(
                filePath, JsonSerializableMainCatalogue.class);
        if (!jsonMainCatalogue.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonMainCatalogue.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveMainCatalogue(ReadOnlyMainCatalogue mainCatalogue) throws IOException {
        saveMainCatalogue(mainCatalogue, filePath);
    }

    /**
     * Similar to {@link #saveMainCatalogue(ReadOnlyMainCatalogue)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveMainCatalogue(ReadOnlyMainCatalogue mainCatalogue, Path filePath) throws IOException {
        requireNonNull(mainCatalogue);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableMainCatalogue(mainCatalogue), filePath);
    }

}
