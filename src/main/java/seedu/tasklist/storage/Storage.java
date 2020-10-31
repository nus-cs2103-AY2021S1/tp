package seedu.tasklist.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.tasklist.commons.exceptions.DataConversionException;
import seedu.tasklist.model.ReadOnlyProductiveNus;
import seedu.tasklist.model.ReadOnlyUserPrefs;
import seedu.tasklist.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends ProductiveNusStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getProductiveNusFilePath();

    @Override
    Optional<ReadOnlyProductiveNus> readProductiveNus() throws DataConversionException, IOException;

    @Override
    void saveProductiveNus(ReadOnlyProductiveNus productiveNus) throws IOException;

}
