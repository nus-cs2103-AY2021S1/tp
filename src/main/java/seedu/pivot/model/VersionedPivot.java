package seedu.pivot.model;

import java.util.Stack;

public class VersionedPivot extends Pivot {

    private Stack<ReadOnlyPivot> pivotUndoHistory = new Stack<>();
    private Stack<ReadOnlyPivot> pivotRedoHistory = new Stack<>();

    public VersionedPivot(ReadOnlyPivot pivot) {
        pivotUndoHistory.push(pivot);
    }

    public boolean canUndo() {
        return pivotUndoHistory.size() != 1;
    }

    public boolean canRedo() {
        return pivotRedoHistory.size() != 0;
    }

    /**
     * Add the current Pivot state into the undo history, and purge the redo history.
     * @param pivot Current Pivot state.
     */
    public void commit(ReadOnlyPivot pivot) {
        pivotUndoHistory.push(pivot);
        pivotRedoHistory.clear();
    }

    /**
     * Undoes the previous Pivot state.
     * @return Previous Pivot state.
     */
    public ReadOnlyPivot undo() {
        ReadOnlyPivot undonePivot = pivotUndoHistory.pop();
        pivotRedoHistory.push(undonePivot);
        return pivotUndoHistory.peek();
    }

    /**
     * Redoes the most recent pivot state.
     * @return Recent pivot state.
     */
    public ReadOnlyPivot redo() {
        ReadOnlyPivot redonePivot = pivotRedoHistory.pop();
        pivotUndoHistory.push(redonePivot);
        return redonePivot;
    }
}
