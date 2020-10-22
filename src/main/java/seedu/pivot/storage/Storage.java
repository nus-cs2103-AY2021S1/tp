package seedu.pivot.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.pivot.commons.exceptions.DataConversionException;
import seedu.pivot.model.ReadOnlyPivot;
import seedu.pivot.model.ReadOnlyUserPrefs;
import seedu.pivot.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends PivotStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getPivotFilePath();

    @Override
    Optional<ReadOnlyPivot> readPivot() throws DataConversionException, IOException;

    @Override
    void savePivot(ReadOnlyPivot pivot) throws IOException;

    void addReferenceTestFile() throws IOException;

}
