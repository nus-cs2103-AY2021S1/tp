package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyWishfulShrinking;

/**
 * Represents a storage for {@link seedu.address.model.WishfulShrinking}.
 */
public interface WishfulShrinkingStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getWishfulShrinkingFilePath();

    /**
     * Returns WishfulShrinking data as a {@link ReadOnlyWishfulShrinking}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyWishfulShrinking> readWishfulShrinking() throws DataConversionException, IOException;

    /**
     * @see #getWishfulShrinkingFilePath()
     */
    Optional<ReadOnlyWishfulShrinking> readWishfulShrinking(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyWishfulShrinking} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveWishfulShrinking(ReadOnlyWishfulShrinking addressBook) throws IOException;

    /**
     * @see #saveWishfulShrinking(ReadOnlyWishfulShrinking)
     */
    void saveWishfulShrinking(ReadOnlyWishfulShrinking addressBook, Path filePath) throws IOException;

}
