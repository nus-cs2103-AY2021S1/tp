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
import seedu.address.model.ReadOnlyPersonAddressBook;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonPersonAddressBookStorage implements PersonAddressBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonPersonAddressBookStorage.class);

    private Path filePath;

    public JsonPersonAddressBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getPersonAddressBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyPersonAddressBook> readPersonAddressBook() throws DataConversionException {
        return readPersonAddressBook(filePath);
    }

    /**
     * Similar to {@link #readPersonAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyPersonAddressBook> readPersonAddressBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializablePersonAddressBook> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializablePersonAddressBook.class);
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
    public void savePersonAddressBook(ReadOnlyPersonAddressBook addressBook) throws IOException {
        savePersonAddressBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #savePersonAddressBook(ReadOnlyPersonAddressBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void savePersonAddressBook(ReadOnlyPersonAddressBook addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializablePersonAddressBook(addressBook), filePath);
    }

}
