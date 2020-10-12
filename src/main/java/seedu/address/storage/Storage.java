package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.ReadOnlyZooKeepBook;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends ZooKeepBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getZooKeepBookFilePath();

    @Override
    Optional<ReadOnlyZooKeepBook> readZooKeepBook() throws DataConversionException, IOException;

    @Override
    void saveZooKeepBook(ReadOnlyZooKeepBook zooKeepBook) throws IOException;

}
