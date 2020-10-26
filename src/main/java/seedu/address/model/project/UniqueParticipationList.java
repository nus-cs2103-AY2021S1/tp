package seedu.address.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.project.exceptions.DuplicateParticipationException;
import seedu.address.model.project.exceptions.DuplicateProjectException;
import seedu.address.model.project.exceptions.ParticipationNotFoundException;

/**
 * A list of projects that enforces uniqueness between its elements and does not allow nulls.
 * A project is considered unique by comparing using {@code Project#isSameProject(Project)}. As such, adding and
 * updating of projects uses Project#isSameProject(Project) for equality so as to ensure that the project being added
 * or updated is unique in terms of identity in the UniqueProjectList. However, the removal of a project uses
 * Project#equals(Object) so as to ensure that the project with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Project#isSameProject(Project)
 */
public class UniqueParticipationList implements Iterable<Participation> {

    private final ObservableList<Participation> internalList = FXCollections.observableArrayList();
    private final ObservableList<Participation> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent project as the given argument.
     */
    public boolean contains(Participation toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameParticipation);
    }

    /**
     * Adds a project to the list.
     * The project must not already exist in the list.
     */
    public void add(Participation toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateParticipationException();
        }
        internalList.add(toAdd);
    }

    public Participation getParticipation(Participation participation) {
        requireNonNull(participation);
        if (!contains(participation)) {
            throw new ParticipationNotFoundException();
        }
        Stream<Participation> filtered = internalList.stream().filter(participation::isSameParticipation);
        return internalList.get(0);
    }

    /**
     * Replaces the project {@code target} in the list with {@code editedProject}.
     * {@code target} must exist in the list.
     * The project identity of {@code editedProject} must not be the same as another existing project in the list.
     */
    public void setParticipation(Participation target, Participation editedParticipation) {
        requireAllNonNull(target, editedParticipation);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ParticipationNotFoundException();
        }

        if (!target.isSameParticipation(editedParticipation) && contains(editedParticipation)) {
            throw new DuplicateParticipationException();
        }

        internalList.set(index, editedParticipation);
    }

    /**
     * Removes the equivalent project from the list.
     * The project must exist in the list.
     */
    public void remove(Participation toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ParticipationNotFoundException();
        }
    }

    public void setParticipations(UniqueParticipationList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code projects}.
     * {@code projects} must not contain duplicate projects.
     */
    public void setParticipations(List<Participation> participations) {
        requireAllNonNull(participations);
        if (!participationsAreUnique(participations)) {
            throw new DuplicateProjectException();
        }

        internalList.setAll(participations);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Participation> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Participation> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        //        boolean test = true;
        //        for (int i = 0; i < internalList.size() - 1; i++) {
        //            test = test && internalList.get(i).equals(((UniqueProjectList)other).internalList.get(i));
        //        }

        return other == this // short circuit if same object
                || (other instanceof UniqueParticipationList // instanceof handles nulls
                //                        && test);
                && internalList.equals(((UniqueParticipationList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code projects} contains only unique projects.
     */
    private boolean participationsAreUnique(List<Participation> participations) {
        for (int i = 0; i < participations.size() - 1; i++) {
            for (int j = i + 1; j < participations.size(); j++) {
                if (participations.get(i).isSameParticipation(participations.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
