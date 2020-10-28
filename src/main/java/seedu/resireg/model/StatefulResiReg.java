package seedu.resireg.model;

import java.util.LinkedList;
import java.util.List;

import seedu.resireg.model.exceptions.NoRedoableStateException;
import seedu.resireg.model.exceptions.NoUndoableStateException;

/**
 * {@code ResiReg} that keeps track of its own history.
 */
public class StatefulResiReg extends ResiReg {

    private final LinkedList<ReadOnlyResiReg> redoStatesStack;
    private final LinkedList<ReadOnlyResiReg> undoStatesStack;
    private ReadOnlyResiReg currState; // current state

    /**
     * Creates a {@code VersionedResiReg} with the given {@code ReadOnlyResiReg}.
     */
    public StatefulResiReg(ReadOnlyResiReg initialState) {
        super(initialState);

        redoStatesStack = new LinkedList<>();
        undoStatesStack = new LinkedList<>();
        this.currState = initialState;
    }

    /**
     * Saves a copy of the current {@code ResiReg} state at the end of
     * the undo stack. Undone states are removed from the redo stack.
     */
    public void save() {
        redoStatesStack.clear();
        undoStatesStack.addLast(new ResiReg(currState));
        currState = new ResiReg(this);
    }

    /**
     * Returns true if {@code undo()} has ResiReg states to undo.
     */
    public boolean canUndo() {
        return !undoStatesStack.isEmpty();
    }

    /**
     * Returns true if {@code redo()} has ResiReg states to redo.
     */
    public boolean canRedo() {
        return !redoStatesStack.isEmpty();
    }

    /**
     * Restores ResiReg to its previous state
     */
    public void undo() {
        if (!canUndo()) {
            throw new NoUndoableStateException();
        }

        redoStatesStack.addLast(new ResiReg(currState));

        ReadOnlyResiReg prev = undoStatesStack.pollLast();
        resetData(prev);
        currState = prev;
    }

    /**
     * Restores ResiReg to its previously undone state
     */
    public void redo() {
        if (!canRedo()) {
            throw new NoRedoableStateException();
        }

        undoStatesStack.addLast(new ResiReg(currState));

        ReadOnlyResiReg prev = redoStatesStack.pollLast();
        resetData(prev);
        currState = prev;
    }

    public List<ReadOnlyResiReg> getUndoStates() {
        return undoStatesStack;
    }

    public List<ReadOnlyResiReg> getRedoStates() {
        return redoStatesStack;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StatefulResiReg)) {
            return false;
        }

        StatefulResiReg otherStatefulResiReg = (StatefulResiReg) other;

        // state check
        return super.equals(otherStatefulResiReg) // certificate for currState
                && undoStatesStack.equals(otherStatefulResiReg.undoStatesStack)
                && redoStatesStack.equals(otherStatefulResiReg.redoStatesStack);
    }
}

