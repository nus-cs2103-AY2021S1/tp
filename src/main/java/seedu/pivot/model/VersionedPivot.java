package seedu.pivot.model;

import java.util.Stack;

public class VersionedPivot extends Pivot {
    private static final String MESSAGE_EMPTY_STACK = "Nothing to undo.";

    private Stack<Pivot> pivotStateStack = new Stack<>();

    public VersionedPivot(Pivot pivot) {
        pivotStateStack.push(pivot);
    }

    public int getStackSize() {
        return pivotStateStack.size();
    }

    public void commit(Pivot pivot) {
        pivotStateStack.push(pivot);
    }

    /**
     * Restores the previous pivot state.
     * @return Previous pivot state.
     */
    public Pivot undo() {
        if (pivotStateStack.size() == 1) {
            // Todo: throw exception
        }
        pivotStateStack.pop();
        return pivotStateStack.peek();
    }
}
