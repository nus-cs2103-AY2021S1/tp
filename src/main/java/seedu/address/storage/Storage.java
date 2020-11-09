package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyGradeBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.GoalTarget;

/**
 * API of the Storage component
 */
public interface Storage extends GradeBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getGradeBookFilePath();

    @Override
    Optional<ReadOnlyGradeBook> readGradeBook() throws DataConversionException, IOException;

    @Override
    void saveGradeBook(ReadOnlyGradeBook gradeBook, GoalTarget goalTarget) throws IOException;

}
