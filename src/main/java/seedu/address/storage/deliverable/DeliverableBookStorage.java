package seedu.address.storage.deliverable;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.deliverable.DeliverableBook;
import seedu.address.model.deliverable.ReadOnlyDeliverableBook;

/**
 * Represents a storage for {@link DeliverableBook}.
 */
public interface DeliverableBookStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getDeliverableBookFilePath();

    /**
     * Returns DeliverableBook data as a {@link ReadOnlyDeliverableBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyDeliverableBook> readDeliverableBook() throws DataConversionException, IOException;

    /**
     * @see #getDeliverableBookFilePath()
     */
    Optional<ReadOnlyDeliverableBook> readDeliverableBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyDeliverableBook} to the storage.
     * @param deliverableBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveDeliverableBook(ReadOnlyDeliverableBook deliverableBook) throws IOException;

    /**
     * @see #saveDeliverableBook(ReadOnlyDeliverableBook)
     */
    void saveDeliverableBook(ReadOnlyDeliverableBook deliverableBook, Path filePath) throws IOException;
}
