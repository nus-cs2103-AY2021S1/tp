package seedu.address.model.quiz;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.quiz.exceptions.DuplicateResponseException;
import seedu.address.model.quiz.exceptions.ResponseNotFoundException;

/**
 * A list of questions that enforces uniqueness between its elements and does not allow nulls.
 * A question is considered unique by comparing using {@code Response#isSameResponse(Response)}.
 * As such, adding and updating of persons uses Response#isSameResponse(Response) for equality so as to ensure
 * that the question being added or updated is unique in terms of identity in the UniquePersonList. However, the
 * removal of a question uses Response#equals(Object) so as to ensure that the question with exactly the same
 * fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Response#isSameResponse(Response)
 */
public class UniqueResponseList implements Iterable<Response> {

    private final ObservableList<Response> internalList = FXCollections.observableArrayList();
    private final ObservableList<Response> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent question as the given argument.
     */
    public boolean contains(Response toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameResponse);
    }

    /**
     * Adds a question to the list.
     * The question must not already exist in the list.
     */
    public void add(Response toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateResponseException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the question {@code target} in the list with {@code editedResponse}.
     * {@code target} must exist in the list.
     * The question identity of {@code editedResponse} must not be the same as another existing question in the list.
     */
    public void setResponse(Response target, Response editedResponse) {
        requireAllNonNull(target, editedResponse);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ResponseNotFoundException();
        }

        if (!target.isSameResponse(editedResponse) && contains(editedResponse)) {
            throw new DuplicateResponseException();
        }

        internalList.set(index, editedResponse);
    }

    /**
     * Removes the equivalent question from the list.
     * The question must exist in the list.
     */
    public void remove(Response toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ResponseNotFoundException();
        }
    }

    public void setResponses(UniqueResponseList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code questions}.
     * {@code questions} must not contain duplicate questions.
     */
    public void setResponses(List<Response> questions) {
        requireAllNonNull(questions);
        if (!questionsAreUnique(questions)) {
            throw new DuplicateResponseException();
        }

        internalList.setAll(questions);
    }

    /**
     * Returns size of internal list
     */
    public int size() {
        return internalList.size();
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Response> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Response> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueResponseList // instanceof handles nulls
                        && internalList.equals(((UniqueResponseList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code questions} contains only unique questions.
     */
    private boolean questionsAreUnique(List<Response> questions) {
        for (int i = 0; i < questions.size() - 1; i++) {
            for (int j = i + 1; j < questions.size(); j++) {
                if (questions.get(i).isSameResponse(questions.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
