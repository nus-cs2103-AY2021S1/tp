package seedu.tr4cker.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.tr4cker.commons.exceptions.DataConversionException;
import seedu.tr4cker.model.ReadOnlyTr4cker;
import seedu.tr4cker.model.ReadOnlyUserPrefs;
import seedu.tr4cker.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends Tr4cker, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getTr4ckerFilePath();

    @Override
    Optional<ReadOnlyTr4cker> readTr4cker() throws DataConversionException, IOException;

    @Override
    void saveTr4cker(ReadOnlyTr4cker tr4cker) throws IOException;

}
