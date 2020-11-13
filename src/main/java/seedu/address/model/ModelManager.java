package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.task.Task;
import seedu.address.timetable.TimetableData;

/**
 * Represents the in-memory model of ProductiveNus data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ProductiveNus productiveNus;
    private final UserPrefs userPrefs;
    private final FilteredList<Assignment> filteredAssignments;
    private final FilteredList<Assignment> remindedAssignments;
    //private final FilteredList<Lesson> lessons;
    private final FilteredList<Task> filteredTasks;
    private Model previousModel;

    /**
     * Initializes a ModelManager with the given productiveNus, userPrefs and previousModel.
     */
    public ModelManager(ReadOnlyProductiveNus productiveNus, ReadOnlyUserPrefs userPrefs, Model previousModel) {
        super();
        requireAllNonNull(productiveNus, userPrefs);

        logger.fine("Initializing with ProductiveNus: " + productiveNus + " and user prefs " + userPrefs);

        this.productiveNus = new ProductiveNus(productiveNus);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredAssignments = new FilteredList<>(this.productiveNus.getAssignmentList());
        remindedAssignments = new FilteredList<>(
                this.productiveNus.getAssignmentList(), PREDICATE_SHOW_ALL_REMINDED_ASSIGNMENTS);
        //lessons = new FilteredList<>(this.productiveNus.getLessonList());
        filteredTasks = new FilteredList<>(this.productiveNus.getTaskList());
        this.previousModel = previousModel;
    }

    /**
     * Initializes a ModelManager with the given productiveNus, userPrefs, previousModel
     * and filterAssignments.
     */
    public ModelManager(ReadOnlyProductiveNus productiveNus, ReadOnlyUserPrefs userPrefs, Model previousModel,
                        FilteredList<Assignment> filteredAssignments) {
        super();
        requireAllNonNull(productiveNus, userPrefs);

        logger.fine("Initializing with ProductiveNus: " + productiveNus + " and user prefs " + userPrefs);

        this.productiveNus = new ProductiveNus(productiveNus);
        this.userPrefs = new UserPrefs(userPrefs);

        this.filteredAssignments = new FilteredList<>(this.productiveNus.getAssignmentList(),
                filteredAssignments.getPredicate());
        remindedAssignments = new FilteredList<>(
                this.productiveNus.getAssignmentList(), PREDICATE_SHOW_ALL_REMINDED_ASSIGNMENTS);
        //lessons = new FilteredList<>(this.productiveNus.getLessonList());
        filteredTasks = new FilteredList<>(this.productiveNus.getTaskList());

        this.previousModel = previousModel;
    }

    public ModelManager() {
        this(new ProductiveNus(), new UserPrefs(), null);
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void preUpdateModel() {
        this.previousModel = new ModelManager(this.productiveNus, this.userPrefs, this.previousModel,
                this.filteredAssignments);
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public void goToPreviousModel() {
        setProductiveNus(previousModel.getProductiveNus());
        setUserPrefs(previousModel.getUserPrefs());
        filteredAssignments.setPredicate(previousModel.getFilteredAssignments().getPredicate());
        setPreviousModel(previousModel.getPreviousModel());
    }

    @Override
    public FilteredList<Assignment> getFilteredAssignments() {
        return filteredAssignments;
    }

    @Override
    public void setPreviousModel(Model previousModel) {
        this.previousModel = previousModel;
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
    public Path getProductiveNusFilePath() {
        return userPrefs.getProductiveNusFilePath();
    }

    @Override
    public void setProductiveNusFilePath(Path productiveNusFilePath) {
        requireNonNull(productiveNusFilePath);
        userPrefs.setProductiveNusFilePath(productiveNusFilePath);
    }

    //=========== ProductiveNus ================================================================================

    @Override
    public void setProductiveNus(ReadOnlyProductiveNus productiveNus) {
        this.productiveNus.resetData(productiveNus);
    }

    @Override
    public ReadOnlyProductiveNus getProductiveNus() {
        return productiveNus;
    }

    @Override
    public void importTimetable(TimetableData data) {
        productiveNus.importTimetable(data);
    }

    @Override
    public boolean hasAssignment(Assignment assignment) {
        requireNonNull(assignment);
        return productiveNus.hasAssignment(assignment);
    }

    @Override
    public void deleteAssignment(Assignment target) {
        productiveNus.removeAssignment(target);
    }

    @Override
    public void addAssignment(Assignment assignment) {
        productiveNus.addAssignment(assignment);
        updateFilteredAssignmentList(PREDICATE_SHOW_ALL_ASSIGNMENT);
    }

    @Override
    public void setAssignment(Assignment target, Assignment editedAssignment) {
        requireAllNonNull(target, editedAssignment);
        productiveNus.setAssignment(target, editedAssignment);
    }

    @Override
    public Model getPreviousModel() {
        return this.previousModel;
    }

    //=========== Filtered Assignment List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Assignment} backed by the internal list of
     * {@code versionedProductiveNus}
     */
    @Override
    public ObservableList<Assignment> getFilteredAssignmentList() {
        return filteredAssignments;
    }

    @Override
    public void updateFilteredAssignmentList(Predicate<Assignment> predicate) {
        requireNonNull(predicate);
        filteredAssignments.setPredicate(predicate);
    }

    //=========== Task List Accessors =============================================================

    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return filteredTasks;
    }

    //=========== Reminded Assignments List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Assignment} backed by the internal list of
     * {@code versionedProductiveNus}
     */
    @Override
    public ObservableList<Assignment> getRemindedAssignmentsList() {
        return remindedAssignments;
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
        return productiveNus.equals(other.productiveNus)
                && userPrefs.equals(other.userPrefs)
                && filteredAssignments.equals(other.filteredAssignments)
                && (previousModel == null || previousModel.equals(other.previousModel))
                && (other.previousModel == null || other.previousModel.equals(previousModel));
    }

}
