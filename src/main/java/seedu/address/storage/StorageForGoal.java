package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyGoalBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

public interface StorageForGoal extends GoalBookStorage, UserPrefsStorage{
    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getGoalBookFilePath();
    
    @Override
    Optional<ReadOnlyGoalBook> readGoalBook() throws DataConversionException, IOException;

    @Override
    void saveGoalBook(ReadOnlyGoalBook goalBook) throws IOException;

}
