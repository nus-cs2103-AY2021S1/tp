package seedu.address.storage.bidstorage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.bidbook.BidBook;
import seedu.address.model.bidbook.ReadOnlyBidBook;

/**
 * Represents a storage for {@link BidBook}.
 */
public interface BidBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getBidBookFilePath();

    /**
     * Returns BidBook data as a {@link ReadOnlyBidBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyBidBook> readBidBook() throws DataConversionException, IOException;

    /**
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     * @see #getBidBookFilePath()
     */
    Optional<ReadOnlyBidBook> readBidBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyBidBook} to the storage.
     * @param bidBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveBidBook(ReadOnlyBidBook bidBook) throws IOException;

    /**
     * @throws IOException if there was any problem writing to the file.
     * @see #saveBidBook(ReadOnlyBidBook)
     */
    void saveBidBook(ReadOnlyBidBook bidBook, Path filePath) throws IOException;

}
