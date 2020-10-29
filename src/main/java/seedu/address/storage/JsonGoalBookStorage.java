package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyExerciseBook;
import seedu.address.model.ReadOnlyGoalBook;
import seedu.address.model.goal.Goal;

public class JsonGoalBookStorage implements GoalBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonExerciseBookStorage.class);

    private Path filePath;

    public JsonGoalBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getGoalBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyGoalBook> readGoalBook() throws DataConversionException {
        return readGoalBook(filePath);
    }

    /**
     * Similar to {@link #readGoalBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyGoalBook> readGoalBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableGoalBook> jsonGoalBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableGoalBook.class);
        if (!jsonGoalBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonGoalBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveGoalBook(ReadOnlyGoalBook goalBook) throws IOException {
        saveGoalBook(goalBook, filePath);
    }

    /**
     * Similar to {@link #saveGoalBook(ReadOnlyGoalBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveGoalBook(ReadOnlyGoalBook goalBook, Path filePath) throws IOException {
        requireNonNull(goalBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableGoalBook(goalBook), filePath);
    }
}
