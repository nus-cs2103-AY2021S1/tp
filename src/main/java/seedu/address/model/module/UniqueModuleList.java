package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.exceptions.DuplicateShowableException;
import seedu.address.model.exceptions.ShowableNotFoundException;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.model.tutorialgroup.UniqueTutorialGroupList;

public class UniqueModuleList implements Iterable<Module> {
    private final ObservableList<Module> internalList = FXCollections.observableArrayList();
    private final ObservableList<Module> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    // Module Operations
    /**
     * Returns true if the list contains an equivalent {@code Showable} object as the given argument.
     */
    public boolean contains(Module toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSame);
    }

    /**
     * Adds an {@code Showable} object to the list.
     * The object must not already exist in the list.
     */
    public void addModule(Module toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateShowableException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the {@code Showable} object {@code target} in the list with {@code editedObject}.
     * {@code target} must exist in the list.
     * The identity of {@code editedObject} must not be the same as another existing object in the list.
     */
    public void setModule(Module target, String newModuleId) {
        requireAllNonNull(target, newModuleId);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ShowableNotFoundException();
        }

        Module moduleWithNewModuleId = new Module(new ModuleId(newModuleId));

        if (!target.isSame(moduleWithNewModuleId) && contains(moduleWithNewModuleId)) {
            throw new DuplicateShowableException();
        }

        internalList.get(index).setId(newModuleId);
    }

    /**
     * Removes the equivalent object from the list.
     * The object must exist in the list.
     */
    public void removeModule(Module toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ShowableNotFoundException();
        }
    }

    public void setModuleList(UniqueModuleList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code objects}.
     * {@code objects} must not contain duplicate objects.
     */
    public void setModuleList(List<Module> objects) {
        requireAllNonNull(objects);
        if (!modulesAreUnique(objects)) {
            throw new DuplicateShowableException();
        }

        internalList.setAll(objects);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Module> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    // Tutorial Group Operations
    /**
     * Returns the tutorial group list of the given module as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<TutorialGroup> getTutorialGroupListOfModule(Module target) {
        int index = internalList.indexOf(target);
        return internalList.get(index).getTutorialGroups();
    }

    public UniqueTutorialGroupList getUniqueTutorialGroupList(Module target) {
        int index = internalList.indexOf(target);
        return internalList.get(index).getUniqueTutorialGroupList();
    }

    /**
     * Adds an {@code Showable} object to the list.
     * The object must not already exist in the list.
     */
    public void addTutorialGroup(TutorialGroup tutorialGroup, Module currentModuleInView) {
        int index = internalList.indexOf(currentModuleInView);
        if (index >= 0) {
            internalList.get(index).addTutorialGroup(tutorialGroup);
        }
    }

    @Override
    public Iterator<Module> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueModuleList // instanceof handles nulls
                && internalList.equals(((UniqueModuleList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code objects} contains only unique objects.
     */
    private boolean modulesAreUnique(List<Module> objects) {
        for (int i = 0; i < objects.size() - 1; i++) {
            for (int j = i + 1; j < objects.size(); j++) {
                if (objects.get(i).isSame(objects.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
