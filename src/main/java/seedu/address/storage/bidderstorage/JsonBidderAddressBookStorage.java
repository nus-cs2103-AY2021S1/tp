package seedu.address.storage.bidderstorage;

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
import seedu.address.model.bidderaddressbook.ReadOnlyBidderAddressBook;

public class JsonBidderAddressBookStorage implements BidderAddressBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonBidderAddressBookStorage.class);

    private Path filePath;

    public JsonBidderAddressBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getBidderAddressBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyBidderAddressBook> readBidderAddressBook() throws DataConversionException {
        return readBidderAddressBook(filePath);
    }

    /**
     * Similar to {@link #readBidderAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyBidderAddressBook> readBidderAddressBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableBidderAddressBook> jsonBidderAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableBidderAddressBook.class);
        if (!jsonBidderAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonBidderAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveBidderAddressBook(ReadOnlyBidderAddressBook sellerAddressBook) throws IOException {
        saveBidderAddressBook(sellerAddressBook, filePath);
    }

    /**
     * Similar to {@link #saveBidderAddressBook(ReadOnlyBidderAddressBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws IOException if the file or directory cannot be created.
     */
    public void saveBidderAddressBook(ReadOnlyBidderAddressBook addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableBidderAddressBook(addressBook), filePath);
    }
}
