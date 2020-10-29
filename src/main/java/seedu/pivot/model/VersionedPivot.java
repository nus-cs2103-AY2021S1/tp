package seedu.pivot.model;

import static java.util.Objects.requireNonNull;
import static seedu.pivot.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;

public class VersionedPivot extends Pivot {

    public static final String INITIAL_COMMAND = "Initial command";
    public static final int INITIAL_STATE = 0;

    private final List<ReadOnlyPivot> pivotStateList = new ArrayList<>();
    private final List<String> commands = new ArrayList<>();

    private int currentStatePointer;

    /**
     * Create a VersionedPivot object with the list of Pivot states being
     * initialised with the initial state. The current state pointer points to the
     * initial state.
     * @param pivot The initial Pivot state.
     */
    public VersionedPivot(ReadOnlyPivot pivot) {
        requireNonNull(pivot);
        pivotStateList.add(pivot);
        commands.add(INITIAL_COMMAND);
        currentStatePointer = INITIAL_STATE;
    }

    /**
     * Create a VersionedPivot object with the fields directly initialised with
     * the arguments.
     * @param pivotStateList The list of Pivot states.
     * @param commands The list of commands corresponding to the Pivot states.
     * @param currentStatePointer The index of the current Pivot state in the pivotStateList.
     */
    public VersionedPivot(List<ReadOnlyPivot> pivotStateList,
            List<String> commands, int currentStatePointer) {
        this.pivotStateList.addAll(pivotStateList);
        this.commands.addAll(commands);
        this.currentStatePointer = currentStatePointer;

    }

    public List<ReadOnlyPivot> getPivotStateList() {
        return this.pivotStateList;
    }

    public List<String> getCommands() {
        return this.commands;
    }

    public int getCurrentStatePointer() {
        return this.currentStatePointer;
    }

    public String getStateCommand() {
        return commands.get(currentStatePointer);
    }

    /**
     * Check if the current state can be undone.
     * @return True if the currentStatePointer is not at the initial state, false otherwise.
     */
    public boolean canUndo() {
        assert currentStatePointer < pivotStateList.size() && currentStatePointer >= 0 : "Index out of bounds";
        return currentStatePointer != INITIAL_STATE;
    }

    /**
     * Check if the current state can be redone.
     * @return True if the currentStatePointer is not at the most recent state, false otherwise.
     */
    public boolean canRedo() {
        assert currentStatePointer < pivotStateList.size() && currentStatePointer >= 0 : "Index out of bounds";
        return currentStatePointer != (pivotStateList.size() - 1);
    }

    /**
     * Remove all states after the current state and add the current Pivot state into the list of Pivot states.
     * @param pivot Current Pivot state.
     */
    public void commit(ReadOnlyPivot pivot, String command) {
        requireAllNonNull(pivot, command);
        pivotStateList.add(pivot);
        commands.add(command);
        currentStatePointer++;

        assert currentStatePointer < pivotStateList.size() : "Index out of bounds";
    }

    /**
     * Remove all states after the current state.
     */
    public void purgeStates() {
        int stateAfterCurrent = currentStatePointer + 1;
        for (int i = stateAfterCurrent; i < pivotStateList.size(); i++) {
            pivotStateList.remove(i);
            commands.remove(i);
        }
    }

    /**
     * Undo the previous Pivot state.
     * @return Previous Pivot state.
     */
    public ReadOnlyPivot undo() {
        currentStatePointer--;
        assert currentStatePointer >= 0 : "Index out of bounds";

        return pivotStateList.get(currentStatePointer);
    }

    /**
     * Redo the most recent pivot state.
     * @return Recent pivot state.
     */
    public ReadOnlyPivot redo() {
        currentStatePointer++;
        assert currentStatePointer < pivotStateList.size() : "Index out of bounds";

        return pivotStateList.get(currentStatePointer);
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
                && otherVersionedPivot.getCommands().equals(getCommands())
                && otherVersionedPivot.getCurrentStatePointer() == getCurrentStatePointer();
    }
}
