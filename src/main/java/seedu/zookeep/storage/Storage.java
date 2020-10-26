package seedu.zookeep.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.zookeep.commons.exceptions.DataConversionException;
import seedu.zookeep.model.ReadOnlyUserPrefs;
import seedu.zookeep.model.ReadOnlyZooKeepBook;
import seedu.zookeep.model.UserPrefs;

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
