package seedu.address.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Student;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class UniqueTutorialGroupList implements Iterable<TutorialGroup> {
    private final ObservableList<TutorialGroup> internalList = FXCollections.observableArrayList();
    private final ObservableList<TutorialGroup> internalUnmodfiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent {@code Showable} object as the given argument.
     */
    public boolean contains(TutorialGroup toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSame);
    }

    /**
     * Adds an {@code Showable} object to the list.
     * The object must not already exist in the list.
     */
    public void addTutorialGroup(TutorialGroup toAdd) {
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
    public void setTutorialGroup(TutorialGroup target, TutorialGroup editedObject) {
        requireAllNonNull(target, editedObject);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ShowableNotFoundException();
        }

        if (!target.isSame(editedObject) && contains(editedObject)) {
            throw new DuplicateShowableException();
        }

        internalList.set(index, editedObject);
    }

    /**
     * Removes the equivalent object from the list.
     * The object must exist in the list.
     */
    public void removeTutorialGroup(TutorialGroup toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ShowableNotFoundException();
        }
    }

    public void setTutorialGroupList(UniqueTutorialGroupList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code objects}.
     * {@code objects} must not contain duplicate objects.
     */
    public void setTutorialGroupList(List<TutorialGroup> objects) {
        requireAllNonNull(objects);
        if (!tutorialGroupsAreUnique(objects)) {
            throw new DuplicateShowableException();
        }

        internalList.setAll(objects);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<TutorialGroup> asUnmodifiableObservableList() {
        return internalUnmodfiableList;
    }

    /**
     * Returns the tutorial group list of the given module as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Student> getStudentListOfTutorialGroup() {
        return FXCollections.observableArrayList();
    }

    @Override
    public Iterator<TutorialGroup> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTutorialGroupList // instanceof handles nulls
                && internalList.equals(((UniqueTutorialGroupList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code objects} contains only unique objects.
     */
    private boolean tutorialGroupsAreUnique(List<TutorialGroup> objects) {
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
