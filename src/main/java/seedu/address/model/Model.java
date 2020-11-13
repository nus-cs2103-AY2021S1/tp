package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.module.Module;
import seedu.address.model.student.Student;
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

    /**
     * Sets the current view to be Module View.
     */
    void setViewToModule();

    /**
     * Sets the current view to be Module View.
     */
    void setCurrentViewToModule();

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
    void setModule(Module target, Module editedModule);

    /**
     * Checks if the current view is the Module View.
     * @return returns isInModuleView
     */
    boolean isInModuleView();

    /**
     * Returns the Module object that is currently in view.
     * @return
     */
    Module getCurrentModuleInView();

    //TutorialGroup Operations

    /**
     * Sets the current view to be the tutorial group list of the given module and filteredTutorialGroup to be it.
     * @param target
     */
    void setViewToTutorialGroup(Module target);

    /**
     * Sets the current view to be the tutorial group view.
     */
    void setCurrentViewToTutorialGroup();

    /**
     * Adds the given module.
     * {@code module} must not already exist in trackr.
     */
    void addTutorialGroup(TutorialGroup tutorialGroup);

    /**
     * Deletes the given tutorial group.
     * The tutorial group must exist in trackr.
     */
    void deleteTutorialGroup(TutorialGroup tutorialGroup);

    /**
     * Returns whether the tutorial group is already inside the currentModuleInView.
     * @param tutorialGroup
     * @return boolean
     */
    boolean hasTutorialGroup(TutorialGroup tutorialGroup);

    /**
     * Replaces the given tutorial group {@code target} with {@code edited}.
     * {@code target} must exist in trackr.
     * The tutorial group identity of {@code edited} must not be the same as another existing tutorial group in trackr.
     */
    void setTutorialGroup(TutorialGroup target, TutorialGroup edited);

    /**
     * Returns whether the current view is the Tutorial Group view.
     * @return isInTutorialGroupView
     */
    boolean isInTutorialGroupView();
    // Student Operations

    /**
     * Sets the view to the student list of the given tutorial group in the currentModuleInView
     * @param target
     */
    void setViewToStudent(TutorialGroup target);

    /**
     * Sets the current view to be the Student view.
     */
    void setCurrentViewToStudent();

    /**
     * Returns the current tutorial group in view.
     * @return currentTgInView
     */
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

    /**
     * Returns whether the current view is the Student view.
     * @return
     */
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
