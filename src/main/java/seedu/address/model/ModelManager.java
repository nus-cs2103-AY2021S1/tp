package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.project.Project;
import seedu.address.model.project.Status;
import seedu.address.model.task.Task;

/**
 * Represents the in-memory model of the main catalogue data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final MainCatalogue mainCatalogue;
    private final UserPrefs userPrefs;
    private final FilteredList<Project> filteredProjects;
    private Optional<Project> projectToBeDisplayedOnDashboard;
    private Optional<Task> taskToBeDisplayedOnDashboard;

    /**
     * Initializes a ModelManager with the given mainCatalogue and userPrefs.
     */
    public ModelManager(ReadOnlyMainCatalogue mainCatalogue, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(mainCatalogue, userPrefs);

        logger.fine("Initializing with main catalogue: " + mainCatalogue + " and user prefs " + userPrefs);

        this.mainCatalogue = new MainCatalogue(mainCatalogue);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredProjects = new FilteredList<>(this.mainCatalogue.getProjectList());
        this.projectToBeDisplayedOnDashboard = Optional.empty();
    }

    public ModelManager() {
        this(new MainCatalogue(), new UserPrefs());
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
    public Path getProjectCatalogueFilePath() {
        return userPrefs.getMainCatalogueFilePath();
    }

    @Override
    public void setProjectCatalogueFilePath(Path mainCatalogueFilePath) {
        requireNonNull(mainCatalogueFilePath);
        userPrefs.setMainCatalogueFilePath(mainCatalogueFilePath);
    }

    //=========== MainCatalogue ================================================================================

    @Override
    public void setProjectCatalogue(ReadOnlyMainCatalogue mainCatalogue) {
        this.mainCatalogue.resetData(mainCatalogue);
    }

    @Override
    public ReadOnlyMainCatalogue getProjectCatalogue() {
        return mainCatalogue;
    }

    @Override
    public boolean hasProject(Project project) {
        requireNonNull(project);
        return mainCatalogue.hasProject(project);
    }

    @Override
    public void deleteProject(Project target) {
        mainCatalogue.removeProject(target);
    }

    @Override
    public void addProject(Project project) {
        mainCatalogue.addProject(project);
        updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
    }

    @Override
    public void setProject(Project target, Project editedProject) {
        requireAllNonNull(target, editedProject);

        mainCatalogue.setProject(target, editedProject);
    }

    //=========== Scoping modifiers ===========================================================================

    @Override
    public Status getStatus() {
        return mainCatalogue.getStatus();
    }

    @Override
    public void enter(Project project) {
        mainCatalogue.enter(project);
        updateProjectToBeDisplayedOnDashboard(project);
    }

    @Override
    public void quit() {
        mainCatalogue.quit();
    }

    @Override
    public void enterTask(Task task) {
        mainCatalogue.enterTask(task);
        updateTaskToBeDisplayedOnDashboard(task);
    }

    //=========== Filtered Project List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Project} backed by the internal list of
     * {@code versionedMainCatalogue}
     */
    @Override
    public ObservableList<Project> getFilteredProjectList() {
        return filteredProjects;
    }

    @Override
    public void updateFilteredProjectList(Predicate<Project> predicate) {
        requireNonNull(predicate);
        filteredProjects.setPredicate(predicate);
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
        return mainCatalogue.equals(other.mainCatalogue)
                && userPrefs.equals(other.userPrefs)
                && filteredProjects.equals(other.filteredProjects);
    }

    //=========== Project To Be Displayed On DashBoard Accessors ======================================================

    /**
     * Updates the project to be displayed on project dashboard.
     * @param project project to be displayed on dashboard
     */
    @Override
    public void updateProjectToBeDisplayedOnDashboard(Project project) {
        requireNonNull(project);
        this.projectToBeDisplayedOnDashboard = Optional.of(project);
    }

    @Override
    public Optional<Project> getProjectToBeDisplayedOnDashboard() {
        return projectToBeDisplayedOnDashboard;
    }

    @Override
    public void updateTaskToBeDisplayedOnDashboard(Task task) {
        requireNonNull(task);
        this.taskToBeDisplayedOnDashboard = Optional.of(task);
    }

    @Override
    public Optional<Task> getTaskToBeDisplayedOnDashboard() {
        return taskToBeDisplayedOnDashboard;
    }
}
