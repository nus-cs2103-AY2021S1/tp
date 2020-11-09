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
import seedu.address.model.ReadOnlyFlashcardBook;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonFlashcardBookStorage implements FlashcardBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonFlashcardBookStorage.class);

    private Path filePath;

    public JsonFlashcardBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getFlashcardBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyFlashcardBook> readFlashcardBook() throws DataConversionException {
        return readFlashcardBook(filePath);
    }

    /**
     * Similar to {@link #readFlashcardBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyFlashcardBook> readFlashcardBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        // Json file is read here
        Optional<JsonSerializableAddressBook> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableAddressBook.class);
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
    public void saveFlashcardBook(ReadOnlyFlashcardBook addressBook) throws IOException {
        saveFlashcardBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveFlashcardBook(ReadOnlyFlashcardBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveFlashcardBook(ReadOnlyFlashcardBook addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath); // save to Json file here
        JsonUtil.saveJsonFile(new JsonSerializableAddressBook(addressBook), filePath);
    }

}
