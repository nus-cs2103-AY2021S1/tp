package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.exceptions.DuplicateShowableException;
import seedu.address.model.exceptions.ShowableNotFoundException;

/**
 * A list of students that enforces uniqueness between its elements and does not allow nulls.
 * A student is considered unique by comparing using {@code Student#isSame(Student)}. As such, adding and updating of
 * students uses Student#isSame(Student) for equality so as to ensure that the student being added or updated is
 * unique in terms of identity in the UniqueStudentList. However, the removal of a student uses Student#equals(Object)
 * so as to ensure that the student with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Student#isSame(Student)
 */
public class UniqueStudentList implements Iterable<Student> {
    private final ObservableList<Student> internalList = FXCollections.observableArrayList();
    private final ObservableList<Student> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent student as the given argument.
     */
    public boolean contains(Student toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSame);
    }

    /**
     * Adds a student to the list.
     * The student must not already exist in the list.
     */
    public void addStudent(Student toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateShowableException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the list.
     * The identity of {@code editedStudent} must not be the same as another existing student in the list.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ShowableNotFoundException();
        }

        if (!target.isSame(editedStudent) && contains(editedStudent)) {
            throw new DuplicateShowableException();
        }

        internalList.set(index, editedStudent);
    }

    /**
     * Removes the equivalent object from the list.
     * The object must exist in the list.
     */
    public void removeStudent(Student toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ShowableNotFoundException();
        }
    }

    public void setStudentList(UniqueStudentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code objects}.
     * {@code objects} must not contain duplicate objects.
     */
    public void setStudentList(List<Student> students) {
        requireAllNonNull(students);
        if (!studentsAreUnique(students)) {
            throw new DuplicateShowableException();
        }

        internalList.setAll(students);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Student> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Student> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueStudentList // instanceof handles nulls
                && internalList.equals(((UniqueStudentList) other).internalList));
    }

    public int count() {
        return this.internalList.size();
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code objects} contains only unique objects.
     */
    private boolean studentsAreUnique(List<Student> students) {
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = i + 1; j < students.size(); j++) {
                if (students.get(i).isSame(students.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
