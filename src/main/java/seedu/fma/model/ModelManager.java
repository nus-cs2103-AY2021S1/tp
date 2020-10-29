package seedu.fma.model;

import static java.util.Objects.requireNonNull;
import static seedu.fma.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.fma.commons.core.GuiSettings;
import seedu.fma.commons.core.LogsCenter;
import seedu.fma.model.exercise.Exercise;
import seedu.fma.model.log.Log;

/**
 * Represents the in-memory model of the log book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final LogBook logBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Log> filteredLogs;
    private final FilteredList<Exercise> filteredExercises;

    /**
     * Initializes a ModelManager with the given logBook and userPrefs.
     */
    public ModelManager(ReadOnlyLogBook logBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(logBook, userPrefs);

        logger.fine("Initializing with log book: " + logBook + " and user prefs " + userPrefs);

        this.logBook = new LogBook(logBook);
        this.userPrefs = new UserPrefs(userPrefs);
        SortedList<Log> sortedLogs = new SortedList<>(this.logBook.getLogList(),
                Comparator.comparing(Log::getDateTime).reversed());
        filteredLogs = new FilteredList<>(sortedLogs);
        filteredExercises = new FilteredList<>(this.logBook.getExerciseList());
    }

    public ModelManager() {
        this(new LogBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getLogBookFilePath() {
        return userPrefs.getLogBookFilePath();
    }

    @Override
    public void setLogBookFilePath(Path logBookFilePath) {
        requireNonNull(logBookFilePath);
        userPrefs.setLogBookFilePath(logBookFilePath);
    }

    //=========== LogBook ================================================================================

    @Override
    public void setLogBook(ReadOnlyLogBook logBook) {
        this.logBook.resetData(logBook);
    }

    @Override
    public ReadOnlyLogBook getLogBook() {
        return logBook;
    }

    @Override
    public boolean hasLog(Log log) {
        requireNonNull(log);
        return logBook.hasLog(log);
    }

    @Override
    public void deleteLog(Log target) {
        logBook.removeLog(target);
    }

    @Override
    public void addLog(Log log) {
        logBook.addLog(log);
        updateFilteredLogList(PREDICATE_SHOW_ALL_LOGS);
    }

    @Override
    public void setLog(Log target, Log editedLog) {
        requireAllNonNull(target, editedLog);

        logBook.setLog(target, editedLog);
    }

    /**
     * Returns true if a exercise with the same identity as {@code exercise} exists in the log book.
     */
    @Override
    public boolean hasExercise(Exercise exercise) {
        requireNonNull(exercise);
        return logBook.hasExercise(exercise);
    }

    /**
     * Deletes the given exercise.
     * The exercise must exist in the log book.
     *
     * @param target exercise to be deleted
     */
    @Override
    public void deleteExercise(Exercise target) {
        logBook.removeExercise(target);
    }

    /**
     * Adds the given exercise.
     * {@code exercise} must not already exist in the log book.
     *
     * @param exercise to be added
     */
    @Override
    public void addExercise(Exercise exercise) {
        logBook.addExercise(exercise);
    }

    /**
     * Replaces the given exercise {@code target} with {@code editedExercise}.
     * {@code target} must exist in the log book.
     * The exercise identity of {@code editedExercise} must not be the same as
     * another existing exercise in the log book.
     *
     * @param target exercise to be replaced
     * @param editedExercise exercise to be set
     */
    @Override
    public void setExercise(Exercise target, Exercise editedExercise) {
        requireAllNonNull(target, editedExercise);

        logBook.setExercise(target, editedExercise);
    }

    //=========== Filtered Log List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Log} backed by the internal list of
     * {@code versionedLogBook}
     */
    @Override
    public ObservableList<Log> getFilteredLogList() {
        return filteredLogs;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Exercise} backed by the internal list of
     * {@code versionedLogBook}
     */
    @Override
    public ObservableList<Exercise> getFilteredExerciseList() {
        return filteredExercises;
    }

    @Override
    public void updateFilteredLogList(Predicate<Log> predicate) {
        requireNonNull(predicate);
        filteredLogs.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return logBook.equals(other.logBook)
                && userPrefs.equals(other.userPrefs)
                && filteredLogs.equals(other.filteredLogs)
                && filteredExercises.equals(other.filteredExercises);
    }

    //=========== Debugging ===============================================================================

    @Override
    public String toString() {
        return "\n====== MODEL MANAGER ======\n"
            + "=== LOGBOOK ===\n"
            + logBook.toString() + "\n"
            + "=== USER PREFS ===\n"
            + userPrefs.toString() + "\n";
    }
}
