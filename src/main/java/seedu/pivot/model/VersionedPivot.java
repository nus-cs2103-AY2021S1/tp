package seedu.pivot.model;

import java.util.ArrayList;

public class VersionedPivot extends Pivot {

    private final ArrayList<ReadOnlyPivot> pivotStateList = new ArrayList<>();
    private final ArrayList<String> commands = new ArrayList<>();

    private final String initialCommand = "Initial command";
    private final int initialState = 0;

    private int currentStatePointer;

    /**
     * Create a VersionedPivot object with the list of pivot states being
     * initialised with the initial state. The current state pointer points to the
     * inital state.
     * @param pivot
     */
    public VersionedPivot(ReadOnlyPivot pivot) {
        pivotStateList.add(pivot);
        commands.add(initialCommand);
        currentStatePointer = initialState;
    }

    public boolean canUndo() {
        return currentStatePointer != initialState;
    }

    public boolean canRedo() {
        return currentStatePointer != (pivotStateList.size() - 1);
    }

    /**
     * Remove all states after the current state and add the current Pivot state into the list of Pivot states.
     * @param pivot Current Pivot state.
     */
    public void commit(ReadOnlyPivot pivot, String command) {
        purgeStates();

        pivotStateList.add(pivot);
        commands.add(command);
        currentStatePointer++;

        System.out.println(pivotStateList.size());
    }

    /**
     * Remove all states after the current state.
     */
    public void purgeStates() {
        int stateAfterCurrent = currentStatePointer + 1;
        for (int i = stateAfterCurrent; i < pivotStateList.size(); i++) {
            pivotStateList.remove(i);
        }
    }

    /**
     * Undo the previous Pivot state.
     * @return Previous Pivot state.
     */
    public ReadOnlyPivot undo() {
        currentStatePointer--;
        return pivotStateList.get(currentStatePointer);
    }

    /**
     * Redo the most recent pivot state.
     * @return Recent pivot state.
     */
    public ReadOnlyPivot redo() {
        currentStatePointer++;
        return pivotStateList.get(currentStatePointer);
    }

    public String getStateCommand() {
        return commands.get(currentStatePointer);
    }
}
