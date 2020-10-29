package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.module.Module;
import seedu.address.model.module.UniqueModuleList;
import seedu.address.model.person.Student;
import seedu.address.model.person.UniqueStudentList;
import seedu.address.model.tutorialgroup.TutorialGroup;

/**
 * Wraps all data at the Trackr level.
 * Duplicates are not allowed (by .isSame comparison).
 */
public class Trackr implements ReadOnlyTrackr<Module> {

    private final UniqueModuleList moduleList;

    public Trackr() {
        moduleList = new UniqueModuleList();
    }

    /**
     * Creates a Trackr using the data in the {@code toBeCopied}
     */
    public Trackr(ReadOnlyTrackr<Module> toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the list with {@code data}.
     * {@code data} must not contain duplicate objects.
     */
    public void setData(List<Module> data) {
        this.moduleList.setModuleList(data);
    }

    /**
     * Resets the existing data of this {@code Trackr} with {@code newData}.
     */
    public void resetData(ReadOnlyTrackr<Module> newData) {
        requireNonNull(newData);
        setData(newData.getList());
    }

    // Module Operations

    /**
     * Returns true if an object with the same identity as {@code object} exists in Trackr.
     */
    public boolean hasModule(Module object) {
        requireNonNull(object);
        return moduleList.contains(object);
    }

    /**
     * Adds an object to Trackr.
     * The object must not already exist in Trackr.
     */
    public void addModule(Module object) {
        requireNonNull(object);
        moduleList.addModule(object);
    }

    /**
     * Replaces the given object {@code target} in the list with {@code editedObject}.
     * {@code target} must exist in Trackr.
     * The identity of {@code editedObject} must not be the same as another existing object.
     */
    public void setModule(Module target, String newModuleId) {
        requireAllNonNull(target, newModuleId);
        moduleList.setModule(target, newModuleId);
    }

    /**
     * Removes {@code key} from this {@code Trackr}.
     * {@code key} must exist in Trackr.
     */
    public void removeModule(Module key) {
        moduleList.removeModule(key);
    }

    //Tutorial Group Operations

    public ObservableList<TutorialGroup> getTutorialGroupListOfModule(Module target) {
        return moduleList.getTutorialGroupListOfModule(target);
    }

    /**
     * Adds an TutorialGroup to Trackr.
     * The object must not already exist in Trackr.
     */
    public void addTutorialGroup(TutorialGroup tutorialGroup, Module currentModuleInView) {
        requireNonNull(tutorialGroup);
        moduleList.addTutorialGroup(tutorialGroup, currentModuleInView);
    }

    /**
     * Deletes a TutorialGroup to Trackr.
     */
    public void deleteTutorialGroup(TutorialGroup tutorialGroupToDelete, Module moduleToDeleteFrom) {
        requireAllNonNull(tutorialGroupToDelete);
        moduleList.getUniqueTutorialGroupList(moduleToDeleteFrom)
            .removeTutorialGroup(tutorialGroupToDelete);
    }

    public void setTutorialGroup(TutorialGroup target, TutorialGroup edited) {
        requireAllNonNull(target, edited);
        for (Module module : moduleList) {
            if (module.getUniqueTutorialGroupList().contains(target)) {
                module.getUniqueTutorialGroupList().setTutorialGroup(target, edited);
            }
        }
    }

    // Student Operations

    public ObservableList<Student> getStudentListOfTutorialGroup(Module targetModule, TutorialGroup targetTG) {
        return moduleList.getUniqueTutorialGroupList(targetModule).getStudentListOfTutorialGroup(targetTG);
    }

    public UniqueStudentList getUniqueStudentList(Module targetModule, TutorialGroup targetTg) {
        return moduleList.getUniqueTutorialGroupList(targetModule).getUniqueStudentList(targetTg);
    }

    /**
     * Adds Student to Trackr.
     */
    public void addStudent(Module targetModule, TutorialGroup targetTg, Student student) {
        requireNonNull(student);
        moduleList.getUniqueTutorialGroupList(targetModule).addStudent(student, targetTg);
    }

    @Override
    public String toString() {
        return moduleList.asUnmodifiableObservableList().size() + " data objects";
    }

    @Override
    public ObservableList<Module> getList() {
        return moduleList.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Trackr // instanceof handles nulls
                && moduleList.equals(((Trackr) other).moduleList));
    }

    @Override
    public int hashCode() {
        return moduleList.hashCode();
    }

}
