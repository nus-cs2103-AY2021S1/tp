package seedu.address.model.quiz;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.AttemptComparator;
import seedu.address.model.quiz.exceptions.AttemptNotFoundException;
import seedu.address.model.quiz.exceptions.DuplicateAttemptException;


/**
 * A list of questions that enforces uniqueness between its elements and does not allow nulls.
 * A question is considered unique by comparing using {@code Attempt#isSameAttempt(Attempt)}.
 * As such, adding and updating of persons uses Attempt#isSameAttempt(Attempt) for equality so as to ensure
 * that the question being added or updated is unique in terms of identity in the UniquePersonList. However, the
 * removal of a question uses Attempt#equals(Object) so as to ensure that the question with exactly the same
 * fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Attempt#isSameAttempt(Attempt)
 */
public class UniqueAttemptList implements Iterable<Attempt> {

    private final ObservableList<Attempt> internalList = FXCollections.observableArrayList();
    private final ObservableList<Attempt> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent question as the given argument.
     */
    public boolean contains(Attempt toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameAttempt);
    }

    /**
     * Adds a question to the list.
     * The question must not already exist in the list.
     */
    public void add(Attempt toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAttemptException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the question {@code target} in the list with {@code editedAttempt}.
     * {@code target} must exist in the list.
     * The question identity of {@code editedAttempt} must not be the same as another existing question in the list.
     */
    public void setAttempt(Attempt target, Attempt editedAttempt) {
        requireAllNonNull(target, editedAttempt);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new AttemptNotFoundException();
        }

        if (!target.isSameAttempt(editedAttempt) && contains(editedAttempt)) {
            throw new DuplicateAttemptException();
        }

        internalList.set(index, editedAttempt);
    }

    /**
     * Removes the equivalent question from the list.
     * The question must exist in the list.
     */
    public void remove(Attempt toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new AttemptNotFoundException();
        }
    }

    public void setAttempts(UniqueAttemptList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code questions}.
     * {@code questions} must not contain duplicate questions.
     */
    public void setAttempts(List<Attempt> questions) {
        requireAllNonNull(questions);
        if (!questionsAreUnique(questions)) {
            throw new DuplicateAttemptException();
        }

        internalList.setAll(questions);
    }

    /**
     * Sorts the question list in either ascending or descending order.
     * @param sortOrder order in which the question list is sorted.
     */
    public void sortAttempts(String sortOrder) {
        AttemptComparator flashcardComparator = new AttemptComparator();
        if (sortOrder.equals("desc")) {
            FXCollections.sort(internalList, flashcardComparator.reversed());
        } else {
            FXCollections.sort(internalList, flashcardComparator);
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Attempt> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Attempt> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueAttemptList // instanceof handles nulls
                        && internalList.equals(((UniqueAttemptList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code questions} contains only unique questions.
     */
    private boolean questionsAreUnique(List<Attempt> questions) {
        for (int i = 0; i < questions.size() - 1; i++) {
            for (int j = i + 1; j < questions.size(); j++) {
                if (questions.get(i).isSameAttempt(questions.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
