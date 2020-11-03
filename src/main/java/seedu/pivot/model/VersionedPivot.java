package seedu.pivot.model;

import static java.util.Objects.requireNonNull;
import static seedu.pivot.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;

public class VersionedPivot extends Pivot {

    public static final String INITIAL_COMMAND = "Initial command";
    public static final int INITIAL_STATE = 0;

    private final List<PivotState> pivotStateList = new ArrayList<>();

    private int currentStatePointer;
    private boolean isMainPageCommandResult = true;
    private String commandResult = "";

    /**
     * Creates a VersionedPivot object with the list of Pivot states being
     * initialised with the initial state. The current state pointer points to the
     * initial state.
     * @param pivot The initial Pivot state.
     */
    public VersionedPivot(ReadOnlyPivot pivot) {
        requireNonNull(pivot);
        pivotStateList.add(new PivotState(pivot, INITIAL_COMMAND, false));
        currentStatePointer = INITIAL_STATE;
    }

    /**
     * Creates a VersionedPivot object with the fields directly initialised with
     * the arguments.
     * @param pivotStateList The list of Pivot states.
     * @param currentStatePointer The index of the current Pivot state in the pivotStateList.
     */
    public VersionedPivot(List<PivotState> pivotStateList, int currentStatePointer) {
        this.pivotStateList.addAll(pivotStateList);
        this.currentStatePointer = currentStatePointer;

    }

    public List<PivotState> getPivotStateList() {
        return this.pivotStateList;
    }

    public int getCurrentStatePointer() {
        return this.currentStatePointer;
    }

    public String getCommandResult() {
        return this.commandResult;
    }

    public boolean getIsMainPageCommandResult() {
        return this.isMainPageCommandResult;
    }

    /**
     * Checks if the current state can be undone.
     * @return True if the currentStatePointer is not at the initial state, false otherwise.
     */
    public boolean canUndo() {
        assert currentStatePointer < pivotStateList.size() && currentStatePointer >= 0 : "Index out of bounds";
        return currentStatePointer != INITIAL_STATE;
    }

    /**
     * Checks if the current state can be redone.
     * @return True if the currentStatePointer is not at the most recent state, false otherwise.
     */
    public boolean canRedo() {
        assert currentStatePointer < pivotStateList.size() && currentStatePointer >= 0 : "Index out of bounds";
        return currentStatePointer != (pivotStateList.size() - 1);
    }

    /**
     * Removes all states after the current state and add the current Pivot state into the list of Pivot states.
     * @param pivot Current Pivot state.
     */
    public void commit(ReadOnlyPivot pivot, String command, boolean isMainPageCommand) {
        requireAllNonNull(pivot, command, isMainPageCommand);
        pivotStateList.add(new PivotState(pivot, command, isMainPageCommand));
        currentStatePointer++;

        assert currentStatePointer < pivotStateList.size() : "Index out of bounds";
    }

    /**
     * Removes all states after the current state.
     */
    public void purgeStates() {
        int stateAfterCurrent = currentStatePointer + 1;
        for (int i = stateAfterCurrent; i < pivotStateList.size(); i++) {
            pivotStateList.remove(i);
        }
    }

    /**
     * Updates the message of the command that was being redone or undone, as well as
     * whether the command was a main page command.
     */
    public void updateRedoUndoResult() {
        PivotState currentPivotState = pivotStateList.get(currentStatePointer);
        commandResult = currentPivotState.command;
        isMainPageCommandResult = currentPivotState.isMainPageCommand;
    }

    /**
     * Undoes the previous Pivot state.
     * @return Previous Pivot state.
     */
    public ReadOnlyPivot undo() {
        updateRedoUndoResult();

        currentStatePointer--;
        assert currentStatePointer >= 0 : "Index out of bounds";

        PivotState newPivotState = pivotStateList.get(currentStatePointer);
        return newPivotState.pivotState;
    }

    /**
     * Redoes the most recent pivot state.
     * @return Recent pivot state.
     */
    public ReadOnlyPivot redo() {
        currentStatePointer++;
        assert currentStatePointer < pivotStateList.size() : "Index out of bounds";

        updateRedoUndoResult();
        PivotState newPivotState = pivotStateList.get(currentStatePointer);
        return newPivotState.pivotState;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof VersionedPivot)) {
            return false;
        }

        VersionedPivot otherVersionedPivot = (VersionedPivot) other;
        return otherVersionedPivot.getPivotStateList().equals(getPivotStateList())
                && otherVersionedPivot.getCurrentStatePointer() == getCurrentStatePointer()
                && otherVersionedPivot.getCommandResult().equals(getCommandResult())
                && otherVersionedPivot.getIsMainPageCommandResult() == getIsMainPageCommandResult();
    }

    private class PivotState {
        final ReadOnlyPivot pivotState;
        final String command;
        final boolean isMainPageCommand;

        private PivotState(ReadOnlyPivot pivotState, String command, boolean isMainPageCommand) {
            this.pivotState = pivotState;
            this.command = command;
            this.isMainPageCommand = isMainPageCommand;
        }
    }
}
