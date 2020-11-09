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
import seedu.address.model.ReadOnlyJobAddressBook;

/**
 * A class to access JobAddressBook data stored as a json file on the hard disk.
 */
public class JsonJobAddressBookStorage implements JobAddressBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonJobAddressBookStorage.class);

    private Path filePath;

    public JsonJobAddressBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getJobAddressBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyJobAddressBook> readJobAddressBook() throws DataConversionException {
        return readJobAddressBook(filePath);
    }

    /**
     * Similar to {@link #readJobAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyJobAddressBook> readJobAddressBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableJobAddressBook> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableJobAddressBook.class);
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
    public void saveJobAddressBook(ReadOnlyJobAddressBook addressBook) throws IOException {
        saveJobAddressBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveJobAddressBook(ReadOnlyJobAddressBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveJobAddressBook(ReadOnlyJobAddressBook addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableJobAddressBook(addressBook), filePath);
    }

}
