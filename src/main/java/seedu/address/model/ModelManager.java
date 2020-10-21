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
import seedu.address.model.module.Module;
import seedu.address.model.person.Student;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Trackr<Student> studentList;
    private final Trackr<Module> moduleList;

    private final FilteredList<Student> filteredStudents;
    private final FilteredList<Module> filteredModules;

    private final UserPrefs userPrefs;

    /**
     * Initializes a ModelManager with the given ReadOnlyTrackrs and userPrefs.
     */
    public ModelManager(ReadOnlyTrackr<Student> studentList, ReadOnlyTrackr<Module> moduleList,
                        ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(studentList, userPrefs);
        logger.fine("Initializing with student data: " + studentList
                + " module data: " + moduleList
                + " and user prefs: " + userPrefs);

        this.studentList = new Trackr<>(studentList);
        this.moduleList = new Trackr<>(moduleList);

        this.filteredStudents = new FilteredList<>(this.studentList.getList());
        this.filteredModules = new FilteredList<>(this.moduleList.getList());

        this.userPrefs = new UserPrefs(userPrefs);
    }

    public ModelManager() {
        this(new Trackr<Student>(), new Trackr<Module>(), new UserPrefs());
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

    //=========== studentList ================================================================================

    @Override
    public void setStudentList(ReadOnlyTrackr<Student> studentList) {
        this.studentList.resetData(studentList);
    }

    @Override
    public ReadOnlyTrackr<Student> getStudentList() {
        return studentList;
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return studentList.hasObject(student);
    }

    @Override
    public void deleteStudent(Student target) {
        studentList.removeObject(target);
    }

    @Override
    public void addStudent(Student student) {
        studentList.addObject(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);
        studentList.setObject(target, editedStudent);
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

    //=========== moduleList ================================================================================

    @Override
    public void setModuleList(ReadOnlyTrackr<Module> moduleList) {
        this.moduleList.resetData(moduleList);
    }

    @Override
    public ReadOnlyTrackr<Module> getModuleList() {
        return moduleList;
    }

    @Override
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return moduleList.hasObject(module);
    }

    @Override
    public void deleteModule(Module target) {
        moduleList.removeObject(target);
    }

    @Override
    public void addModule(Module module) {
        moduleList.addObject(module);
        updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
    }

    @Override
    public void setModule(Module target, Module editedModule) {
        requireAllNonNull(target, editedModule);
        moduleList.setObject(target, editedModule);
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
        return studentList.equals(other.studentList)
                && userPrefs.equals(other.userPrefs)
                && filteredStudents.equals(other.filteredStudents);
    }

}
