package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.module.Module;
import seedu.address.model.person.Student;
import seedu.address.model.tutorialgroup.TutorialGroup;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Trackr moduleList;

    private FilteredList<Module> filteredModules;
    private FilteredList<TutorialGroup> filteredTutorialGroup;
    private FilteredList<Student> filteredStudents;

    private final UserPrefs userPrefs;
    private Module currentModuleInView;
    private TutorialGroup currentTgInView;

    private boolean isInModuleView;
    private boolean isInTutorialGroupView;
    private boolean isInStudentView;

    /**
     * Initializes a ModelManager with the given ReadOnlyTrackrs and userPrefs.
     */
    public ModelManager(ReadOnlyTrackr<Module> moduleList,
                        ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(moduleList, userPrefs);
        logger.fine("Initializing with module data: " + moduleList + " and user prefs: " + userPrefs);

        this.moduleList = new Trackr(moduleList);

        this.filteredModules = new FilteredList<>(this.moduleList.getList());
        this.filteredTutorialGroup = new FilteredList<>(FXCollections.observableArrayList());
        this.filteredStudents = new FilteredList<>(FXCollections.observableArrayList());

        this.userPrefs = new UserPrefs(userPrefs);

        this.isInModuleView = true;
        this.isInTutorialGroupView = false;
        this.isInStudentView = false;
    }

    public ModelManager() {
        this(new Trackr(), new UserPrefs());
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
    public Path getTrackrFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setTrackrFilePath(Path trackrFilePath) {
        requireNonNull(trackrFilePath);
        userPrefs.setAddressBookFilePath(trackrFilePath);
    }

    //=========== moduleList ================================================================================

    @Override
    public void setModuleList(ReadOnlyTrackr<Module> moduleList) {
        this.moduleList.resetData(moduleList);
    }

    @Override
    public ReadOnlyTrackr<Module> getModuleList() {
        return moduleList;
    }

    //=========== Module Operations ================================================================================

    @Override
    public void setViewToModule() {
        this.isInModuleView = true;
        this.isInTutorialGroupView = false;
        this.isInStudentView = false;
    }

    @Override
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return moduleList.hasModule(module);
    }

    @Override
    public void deleteModule(Module target) {
        moduleList.removeModule(target);
    }

    @Override
    public void addModule(Module module) {
        moduleList.addModule(module);
        updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
    }

    @Override
    public void setModule(Module target, String newModuleId) {
        requireAllNonNull(target, newModuleId);
        moduleList.setModule(target, newModuleId);
    }

    @Override
    public boolean isInModuleView() {
        return this.isInModuleView;
    }

    @Override
    public Module getCurrentModuleInView() {
        return currentModuleInView;
    }

    public void setCurrentModuleInView(Module module) {
        this.currentModuleInView = module;
    }

    //=========== TutorialGroup Operations ====================================================================

    @Override
    public void setViewToTutorialGroup(Module target) {
        this.isInModuleView = false;
        this.isInTutorialGroupView = true;
        this.isInStudentView = false;
        currentModuleInView = target;
        filteredTutorialGroup = new FilteredList<>(moduleList.getTutorialGroupListOfModule(target));
    }

    @Override
    public void addTutorialGroup(TutorialGroup target) {
        moduleList.addTutorialGroup(target, currentModuleInView);
        filteredTutorialGroup = new FilteredList<>(moduleList.getTutorialGroupListOfModule(currentModuleInView));
    }

    @Override
    public void deleteTutorialGroup(TutorialGroup tutorialGroup) {
        moduleList.deleteTutorialGroup(tutorialGroup, currentModuleInView);
    }

    @Override
    public boolean hasTutorialGroup(TutorialGroup toCheck) {
        filteredTutorialGroup = new FilteredList<>(moduleList.getTutorialGroupListOfModule(currentModuleInView));
        return filteredTutorialGroup.contains(toCheck);
    }

    @Override
    public void setTutorialGroup(TutorialGroup target, TutorialGroup edited) {
        requireAllNonNull(target, edited);
        moduleList.setTutorialGroup(target, edited);
    }

    @Override
    public boolean isInTutorialGroupView() {
        return this.isInTutorialGroupView;
    }

    @Override
    public TutorialGroup getCurrentTgInView() {
        return currentTgInView;
    }

    public void setCurrentTgInView(TutorialGroup tutorialGroup) {
        this.currentTgInView = tutorialGroup;
    }

    //=========== Student Operations =============================================================================

    @Override
    public void setViewToStudent(TutorialGroup target) {
        this.isInModuleView = false;
        this.isInTutorialGroupView = false;
        this.isInStudentView = true;
        currentTgInView = target;
        filteredStudents =
                new FilteredList<>(moduleList.getStudentListOfTutorialGroup(currentModuleInView, target));
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return moduleList
                .getUniqueStudentList(currentModuleInView, currentTgInView)
                .contains(student);
    }

    @Override
    public void deleteStudent(Student target) {
        moduleList
                .getUniqueStudentList(currentModuleInView, currentTgInView)
                .removeStudent(target);
    }

    @Override
    public void addStudent(Student student) {
        moduleList.addStudent(currentModuleInView, currentTgInView, student);
        filteredStudents =
                new FilteredList<>(moduleList.getStudentListOfTutorialGroup(currentModuleInView, currentTgInView));
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);
        moduleList
                .getUniqueStudentList(currentModuleInView, currentTgInView)
                .setStudent(target, editedStudent);
    }

    @Override
    public boolean isInStudentView() {
        return this.isInStudentView;
    }

    //=========== Filtered Module List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Module}.
     */
    @Override
    public ObservableList<Module> getFilteredModuleList() {
        return filteredModules;
    }

    @Override
    public void updateFilteredModuleList(Predicate<Module> predicate) {
        requireNonNull(predicate);
        filteredModules.setPredicate(predicate);
    }

    //=========== Filtered TutorialGroup List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Module}.
     */
    @Override
    public ObservableList<TutorialGroup> getFilteredTutorialGroupList() {
        return filteredTutorialGroup;
    }

    @Override
    public void updateFilteredTutorialGroupList(Predicate<TutorialGroup> predicate) {
        requireNonNull(predicate);
        filteredTutorialGroup.setPredicate(predicate);
    }

    //=========== Filtered Student List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Student}.
     */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
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
        return moduleList.equals(other.moduleList)
                && userPrefs.equals(other.userPrefs)
                && filteredStudents.equals(other.filteredStudents)
                && filteredTutorialGroup.equals(other.filteredTutorialGroup)
                && filteredModules.equals(other.filteredModules);
    }

}
