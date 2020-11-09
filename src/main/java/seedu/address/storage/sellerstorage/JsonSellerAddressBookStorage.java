package seedu.address.storage.sellerstorage;

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
import seedu.address.model.selleraddressbook.ReadOnlySellerAddressBook;

public class JsonSellerAddressBookStorage implements SellerAddressBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonSellerAddressBookStorage.class);

    private Path filePath;

    public JsonSellerAddressBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getSellerAddressBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlySellerAddressBook> readSellerAddressBook() throws DataConversionException {
        return readSellerAddressBook(filePath);
    }

    /**
     * Similar to {@link #readSellerAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlySellerAddressBook> readSellerAddressBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableSellerAddressBook> jsonSellerAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableSellerAddressBook.class);
        if (!jsonSellerAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonSellerAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveSellerAddressBook(ReadOnlySellerAddressBook sellerAddressBook) throws IOException {
        saveSellerAddressBook(sellerAddressBook, filePath);
    }

    /**
     * Similar to {@link #saveSellerAddressBook(ReadOnlySellerAddressBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws IOException if the file or directory cannot be created.
     */
    public void saveSellerAddressBook(ReadOnlySellerAddressBook addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableSellerAddressBook(addressBook), filePath);
    }
}
