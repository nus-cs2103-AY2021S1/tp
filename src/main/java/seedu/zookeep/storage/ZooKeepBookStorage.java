package seedu.zookeep.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.zookeep.commons.exceptions.DataConversionException;
import seedu.zookeep.model.ReadOnlyZooKeepBook;
import seedu.zookeep.model.ZooKeepBook;

/**
 * Represents a storage for {@link ZooKeepBook}.
 */
public interface ZooKeepBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getZooKeepBookFilePath();

    /**
     * Returns ZooKeepBook data as a {@link ReadOnlyZooKeepBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyZooKeepBook> readZooKeepBook() throws DataConversionException, IOException;

    /**
     * @see #getZooKeepBookFilePath()
     */
    Optional<ReadOnlyZooKeepBook> readZooKeepBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyZooKeepBook} to the storage.
     * @param zooKeepBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveZooKeepBook(ReadOnlyZooKeepBook zooKeepBook) throws IOException;

    /**
     * @see #saveZooKeepBook(ReadOnlyZooKeepBook)
     */
    void saveZooKeepBook(ReadOnlyZooKeepBook zooKeepBook, Path filePath) throws IOException;

}
