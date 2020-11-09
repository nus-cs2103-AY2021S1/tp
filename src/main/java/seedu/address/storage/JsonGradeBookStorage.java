package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyGradeBook;
import seedu.address.model.module.GoalTarget;

/**
 * A class to access GradeBook data stored as a json file on the hard disk.
 */
public class JsonGradeBookStorage implements GradeBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonGradeBookStorage.class);

    private Path filePath;

    public JsonGradeBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getGradeBookFilePath() {
        return filePath;
    }

    public GoalTarget getGoalTarget() throws DataConversionException {

        requireNonNull(filePath);

        Optional<JsonSerializableGradeBook> jsonGradeBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableGradeBook.class);
        if (jsonGradeBook.isEmpty()) {
            return new GoalTarget();
        }

        return jsonGradeBook.get().getGoalTarget();
    }

    @Override
    public Optional<ReadOnlyGradeBook> readGradeBook() throws DataConversionException {
        return readGradeBook(filePath);
    }

    /**
     * Similar to {@link #readGradeBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyGradeBook> readGradeBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableGradeBook> jsonGradeBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableGradeBook.class);
        if (!jsonGradeBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonGradeBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveGradeBook(ReadOnlyGradeBook gradeBook, GoalTarget goalTarget) throws IOException {
        saveGradeBook(gradeBook, filePath, goalTarget);
    }

    /**
     * Similar to {@link GradeBookStorage#saveGradeBook(ReadOnlyGradeBook, GoalTarget)}.
     *
     * @param filePath location of the data. Cannot be null.
     * @param goalTarget
     */
    public void saveGradeBook(ReadOnlyGradeBook gradeBook, Path filePath, GoalTarget goalTarget)
            throws IOException {
        requireNonNull(gradeBook);
        requireNonNull(filePath);
        requireNonNull(goalTarget);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableGradeBook(gradeBook, goalTarget), filePath);
    }

}
