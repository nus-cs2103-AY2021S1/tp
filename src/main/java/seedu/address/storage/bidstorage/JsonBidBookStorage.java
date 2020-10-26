package seedu.address.storage.bidstorage;

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
import seedu.address.model.bidbook.ReadOnlyBidBook;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonBidBookStorage implements BidBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonBidBookStorage.class);

    private Path filePath;

    public JsonBidBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * retrieves the file path for BidBook
     * @return
     */
    public Path getBidBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyBidBook> readBidBook() throws DataConversionException, IOException {
        return readBidBook(filePath);
    }

    /**
     * Similar to {@link #readBidBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyBidBook> readBidBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableBidBook> jsonBidBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableBidBook.class);
        if (!jsonBidBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonBidBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveBidBook(ReadOnlyBidBook bidBook) throws IOException {
        saveBidBook(bidBook, filePath);
    }

    /**
     * Similar to {@link #saveBidBook(ReadOnlyBidBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws IOException if the file or directory cannot be created.
     */
    public void saveBidBook(ReadOnlyBidBook bidBook, Path filePath) throws IOException {
        requireNonNull(bidBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableBidBook(bidBook), filePath);
    }

}
