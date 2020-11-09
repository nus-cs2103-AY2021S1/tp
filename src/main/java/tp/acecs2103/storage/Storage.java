package tp.acecs2103.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import tp.acecs2103.commons.exceptions.DataConversionException;
import tp.acecs2103.model.ReadOnlyUserPrefs;
import tp.acecs2103.model.TaskList;
import tp.acecs2103.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends TaskListStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getTaskListFilePath();

    @Override
    Optional<TaskList> readTaskList() throws DataConversionException, IOException;

    @Override
    void saveTaskList(TaskList taskList) throws IOException;

}
