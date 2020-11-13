package seedu.jarvis.model.masterycheck;

import static java.util.Objects.requireNonNull;
import static seedu.jarvis.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.jarvis.model.masterycheck.exceptions.DuplicateMasteryCheckException;
import seedu.jarvis.model.masterycheck.exceptions.MasteryCheckNotFoundException;

public class MasteryCheckList {
    private final ObservableList<MasteryCheck> internalList = FXCollections.observableArrayList();

    /**
     * Adds a mastery check to the list.
     */
    public void add(MasteryCheck toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Returns true if the list contains an equivalent mastery check as the given argument.
     */
    public boolean contains(MasteryCheck toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameConsultation);
    }

    /**
     * Replaces the contents of this list with {@code masteryChecks}.
     * {@code masteryChecks} must not contain duplicate mastery checks.
     */
    public void setMasteryChecks(List<MasteryCheck> masteryChecks) {
        requireNonNull(masteryChecks);
        this.internalList.setAll(masteryChecks);
    }

    public ObservableList<MasteryCheck> asObservableList() {
        return internalList;
    }

    /**
     * Replaces the mastery check {@code target} in the list with {@code editedMasteryCheck}.
     * {@code target} must exist in the list.
     */
    public void setMasteryCheck(MasteryCheck target, MasteryCheck editedMasteryCheck) {
        requireAllNonNull(target, editedMasteryCheck);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new MasteryCheckNotFoundException();
        }

        if (!target.isSameConsultation(editedMasteryCheck) && contains(editedMasteryCheck)) {
            throw new DuplicateMasteryCheckException();
        }

        internalList.set(index, editedMasteryCheck);
    }

    /**
     * Removes the equivalent mastery check from the list.
     * The mastery check must exist in the list.
     */
    public void remove(MasteryCheck toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new MasteryCheckNotFoundException();
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof MasteryCheckList
                && internalList.equals(((MasteryCheckList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
