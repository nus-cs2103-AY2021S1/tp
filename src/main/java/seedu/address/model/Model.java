package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Module> PREDICATE_SHOW_ALL_MODULES = unused -> true;

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
     * Returns the user prefs' address book file path.
     */
    Path getModuleListFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setModuleListFilePath(Path moduleListFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setModuleList(ReadOnlyModuleList moduleList);

    /** Returns the AddressBook */
    ReadOnlyModuleList getModuleList();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasModule(Module module);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deleteModule(Module target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addModule(Module module);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setModule(Module target, Module editedModule);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Module> getFilteredModuleList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredModuleList(Predicate<Module> predicate);

    //    /**
    //     * Adds the given tutorial group.
    //     * {@code tutorial group} must not already exist in the address book.
    //     */
    //    void addTutorialGroup(TutorialGroup tutorialGroup);
    //
    //    /**
    //     * Returns true if a Tutorial Group with the same id as {@code person} exists in the address book.
    //     */
    //    boolean hasTutorialGroup(TutorialGroup tutorialGroup);
}
