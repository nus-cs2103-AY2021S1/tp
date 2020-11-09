package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.GradeBook;
import seedu.address.model.ReadOnlyGradeBook;
import seedu.address.model.module.GoalTarget;

/**
 * Represents a storage for {@link GradeBook}.
 */
public interface GradeBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getGradeBookFilePath();

    GoalTarget getGoalTarget() throws DataConversionException;

    /**
     * Returns GradeBook data as a {@link ReadOnlyGradeBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyGradeBook> readGradeBook() throws DataConversionException, IOException;

    /**
     * @see #getGradeBookFilePath()
     */
    Optional<ReadOnlyGradeBook> readGradeBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyGradeBook} to the storage.
     * @param gradeBook cannot be null.
     * @param goalTarget
     * @throws IOException if there was any problem writing to the file.
     */
    void saveGradeBook(ReadOnlyGradeBook gradeBook, GoalTarget goalTarget) throws IOException;

    /**
     * @see #saveGradeBook(ReadOnlyGradeBook, GoalTarget)
     */
    void saveGradeBook(ReadOnlyGradeBook gradeBook, Path filePath, GoalTarget goalTarget) throws IOException;

}
