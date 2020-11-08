package seedu.pivot.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.model.investigationcase.UniqueCaseList;

/**
 * Wraps all data at the PIVOT level
 * Duplicates are not allowed (by .isSameCase comparison)
 */
public class Pivot implements ReadOnlyPivot {

    private final UniqueCaseList cases;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        cases = new UniqueCaseList();
    }

    public Pivot() {}

    /**
     * Creates a PIVOT using the Cases in the {@code toBeCopied}
     */
    public Pivot(ReadOnlyPivot toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the case list with {@code cases}.
     * {@code cases} must not contain duplicate cases.
     */
    public void setCases(List<Case> cases) {
        this.cases.setCases(cases);
    }

    /**
     * Resets the existing data of this {@code PIVOT} with {@code newData}.
     */
    public void resetData(ReadOnlyPivot newData) {
        requireNonNull(newData);

        setCases(newData.getCaseList());
    }

    //// case-level operations

    /**
     * Returns true if a case with the same identity as {@code case} exists in PIVOT.
     */
    public boolean hasCase(Case investigationCase) {
        requireNonNull(investigationCase);
        return cases.contains(investigationCase);
    }

    /**
     * Adds a case to PIVOT.
     * The case must not already exist in PIVOT.
     */
    public void addCase(Case p) {
        cases.add(p);
    }

    /**
     * Replaces the given case {@code target} in the list with {@code editedCase}.
     * {@code target} must exist in PIVOT.
     * The case identity of {@code editedCase} must not be the same as another existing case in PIVOT.
     */
    public void setCase(Case target, Case editedCase) {
        requireNonNull(editedCase);

        cases.setCase(target, editedCase);
    }

    /**
     * Removes {@code key} from this {@code PIVOT}.
     * {@code key} must exist in PIVOT.
     */
    public void removeCase(Case key) {
        cases.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return cases.asUnmodifiableObservableList().size() + " cases";
        // TODO: refine later
    }

    @Override
    public ObservableList<Case> getCaseList() {
        return cases.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Pivot // instanceof handles nulls
                && cases.equals(((Pivot) other).cases));
    }

    @Override
    public int hashCode() {
        return cases.hashCode();
    }
}
