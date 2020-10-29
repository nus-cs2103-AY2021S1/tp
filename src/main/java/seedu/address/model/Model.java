package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleId;
import seedu.address.model.person.Student;
import seedu.address.model.tutorialgroup.TutorialGroup;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Module> PREDICATE_SHOW_ALL_MODULES = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<TutorialGroup> PREDICATE_SHOW_ALL_TUTORIALGROUPS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;

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
     * Returns the user prefs' Trackr file path.
     */
    Path getTrackrFilePath();

    /**
     * Sets the user prefs' Trackr file path.
     */
    void setTrackrFilePath(Path trackrFilePath);

    // Module Operations

    /**
     * Replaces Trackr data with the data in {@code moduleList}.
     */
    void setModuleList(ReadOnlyTrackr<Module> moduleList);

    /** Returns the module Trackr */
    ReadOnlyTrackr<Module> getModuleList();

    void setViewToModule();

    /**
     * Returns true if a module with the same identity as {@code module} exists in trackr.
     */
    boolean hasModule(Module module);

    /**
     * Deletes the given module.
     * The module must exist in trackr.
     */
    void deleteModule(Module target);

    /**
     * Adds the given module.
     * {@code module} must not already exist in trackr.
     */
    void addModule(Module module);

    /**
     * Replaces the given module {@code target} with {@code editedModule}.
     * {@code target} must exist in trackr.
     * The module identity of {@code editedModule} must not be the same as another existing module in trackr.
     */
    void setModule(Module target, ModuleId newModuleId);

    boolean isInModuleView();

    Module getCurrentModuleInView();

    //TutorialGroup Operations

    /**
     * Sets the view to the tutorial group list of the given module
     * @param target
     */
    void setViewToTutorialGroup(Module target);

    /**
     * Adds the given module.
     * {@code module} must not already exist in trackr.
     */
    void addTutorialGroup(TutorialGroup tutorialGroup);

    void deleteTutorialGroup(TutorialGroup tutorialGroup);

    boolean hasTutorialGroup(TutorialGroup tutorialGroup);

    void setTutorialGroup(TutorialGroup target, TutorialGroup edited);

    boolean isInTutorialGroupView();
    // Student Operations

    /**
     * Sets the view to the tutorial group list of the given module
     * @param target
     */
    void setViewToStudent(TutorialGroup target);

    TutorialGroup getCurrentTgInView();

    /**
     * Returns true if a student with the same identity as {@code student} exists in trackr.
     */
    boolean hasStudent(Student student);

    /**
     * Deletes the given student.
     * The student must exist in trackr.
     */
    void deleteStudent(Student target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in trackr.
     */
    void addStudent(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in trackr.
     * The student identity of {@code editedStudent} must not be the same as another existing student in trackr.
     */
    void setStudent(Student target, Student editedStudent);

    boolean isInStudentView();

    // FilteredList Operations

    /** Returns an unmodifiable view of the filtered module list */
    ObservableList<Module> getFilteredModuleList();

    /**
     * Updates the filter of the filtered module list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredModuleList(Predicate<Module> predicate);

    /** Returns an unmodifiable view of the filtered tutorial group list */
    ObservableList<TutorialGroup> getFilteredTutorialGroupList();

    /**
     * Updates the filter of the filtered tutorial group list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTutorialGroupList(Predicate<TutorialGroup> predicate);

    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);
}
