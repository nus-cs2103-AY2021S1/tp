package jimmy.mcgymmy.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import jimmy.mcgymmy.commons.core.LogsCenter;
import jimmy.mcgymmy.commons.exceptions.DataConversionException;
import jimmy.mcgymmy.commons.exceptions.IllegalValueException;
import jimmy.mcgymmy.commons.util.FileUtil;
import jimmy.mcgymmy.commons.util.JsonUtil;
import jimmy.mcgymmy.model.ReadOnlyMcGymmy;

/**
 * A class to access McGymmy data stored as a json file on the hard disk.
 */
public class JsonMcGymmyStorage implements McGymmyStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonMcGymmyStorage.class);

    private Path filePath;

    public JsonMcGymmyStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAddressBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyMcGymmy> readAddressBook() throws DataConversionException {
        return readAddressBook(filePath);
    }

    /**
     * Similar to {@link #readAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyMcGymmy> readAddressBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableMcGymmy> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableMcGymmy.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveAddressBook(ReadOnlyMcGymmy addressBook) throws IOException {
        saveAddressBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveAddressBook(ReadOnlyMcGymmy)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAddressBook(ReadOnlyMcGymmy addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableMcGymmy(addressBook), filePath);
    }

}
