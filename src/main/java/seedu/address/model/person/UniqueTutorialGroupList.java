package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.TutorialGroup;
import seedu.address.model.person.exceptions.DuplicateTutorialGroupException;

/**
 * A list of tutorial groups that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code TutorialGroup#isSameTutorialGroup}.
 * As such, adding and updating of Tutorial Groups uses TutorialGroup#isSameTutorialGroup for equality so as to ensure
 * that the TutorialGroup being added or updated is unique in terms of identity in the UniqueTutorialGroupList.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see TutorialGroup#isSameTutorialGroup(TutorialGroup)
 */
public class UniqueTutorialGroupList implements Iterable<TutorialGroup> {

    private final ObservableList<TutorialGroup> internalList = FXCollections.observableArrayList();
    private final ObservableList<TutorialGroup> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent TutorialGroup as the given argument.
     */
    public boolean contains(TutorialGroup toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTutorialGroup);
    }

    /**
     * Adds a TutorialGroup to the list.
     * The TutorialGroup must not already exist in the list.
     */
    public void add(TutorialGroup toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTutorialGroupException(); //change this shit remember
        }
        internalList.add(toAdd);
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
}

