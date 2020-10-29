package seedu.address.storage;


import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyGoalBook;

/**
 * Represents a storage for {@link seedu.address.model.GoalBook}.
 */
public interface GoalBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getGoalBookFilePath();

    /**
     * Returns GoalBook data as a {@link ReadOnlyGoalBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyGoalBook> readGoalBook() throws DataConversionException, IOException;

    /**
     * @see #getGoalBookFilePath()
     */
    Optional<ReadOnlyGoalBook> readGoalBook(Path filePath) throws DataConversionException, IOException;
    
    /**
     * Saves the given {@link ReadOnlyGoalBook} to the storage.
     *
     * @param goalBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveGoalBook(ReadOnlyGoalBook goalBook) throws IOException;

    /**
     * @see #saveGoalBook(ReadOnlyGoalBook)
     */
    void saveGoalBook(ReadOnlyGoalBook addressBook, Path filePath) throws IOException;

}
