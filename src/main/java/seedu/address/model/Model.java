package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.project.Project;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Project> PREDICATE_SHOW_ALL_PROJECTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' main catalogue file path.
     */
    Path getProjectCatalogueFilePath();

    /**
     * Sets the user prefs' main catalogue file path.
     */
    void setProjectCatalogueFilePath(Path mainCatalogueFilePath);

    /**
     * Replaces main catalogue data with the data in {@code mainCatalogue}.
     */
    void setProjectCatalogue(ReadOnlyMainCatalogue mainCatalogue);

    /** Returns the MainCatalogue */
    ReadOnlyMainCatalogue getProjectCatalogue();

    /**
     * Returns true if a project with the same identity as {@code project} exists in the main catalogue.
     */
    boolean hasProject(Project project);

    /**
     * Deletes the given project.
     * The project must exist in the main catalogue.
     */
    void deleteProject(Project target);

    /**
     * Adds the given project.
     * {@code project} must not already exist in the main catalogue.
     */
    void addProject(Project project);

    /**
     * Replaces the given project {@code target} with {@code editedProject}.
     * {@code target} must exist in the main catalogue.
     * The project identity of {@code editedProject} must not be the same as another existing project in the main
     * catalogue.
     */
    void setProject(Project target, Project editedProject);

    /** Returns an unmodifiable view of the filtered project list */
    ObservableList<Project> getFilteredProjectList();

    /**
     * Updates the filter of the filtered project list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredProjectList(Predicate<Project> predicate);
    //boolean hasPerson(Person person);
    //void deletePerson(Person person);
    //void setPerson(Person target, Person editedPerson);
}
