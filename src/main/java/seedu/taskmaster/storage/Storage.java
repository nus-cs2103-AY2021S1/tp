package seedu.taskmaster.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.taskmaster.commons.exceptions.DataConversionException;
import seedu.taskmaster.model.ReadOnlyTaskmaster;
import seedu.taskmaster.model.ReadOnlyUserPrefs;
import seedu.taskmaster.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends TaskmasterStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getTaskmasterFilePath();

    @Override
    Optional<ReadOnlyTaskmaster> readTaskmaster() throws DataConversionException, IOException;

    @Override
    void saveTaskmaster(ReadOnlyTaskmaster taskmaster) throws IOException;

    boolean fileExists(Path filepath);
}
