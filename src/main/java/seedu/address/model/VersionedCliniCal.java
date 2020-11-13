package seedu.address.model;

import java.util.ArrayList;

/**
 * Keeps track of CliniCal states.
 */
public class VersionedCliniCal extends CliniCal {

    private static final int INITIAL_POINTER = 0;

    private final ArrayList<ReadOnlyCliniCal> clinicalStateList;

    private final ArrayList<String> commandList;

    private final CliniCal cliniCal;

    private int currentStatePointer;

    /**
     * Initializes and saves the initial {@code CliniCal} state on first start of CliniCal and assigns the
     * {@code currentStatePointer} to it.
     */
    public VersionedCliniCal(CliniCal initialCliniCal) {
        clinicalStateList = new ArrayList<>();
        commandList = new ArrayList<>();
        currentStatePointer = INITIAL_POINTER;
        this.cliniCal = initialCliniCal;
        clinicalStateList.add(new CliniCal(initialCliniCal));
        commandList.add("Initial patient-list");
    }

    /**
     * Saves the current CliniCal application state in its history, after purging redundant states.
     */
    public void commit(ReadOnlyCliniCal currentCliniCal, String command) {
        assert currentStatePointer > -1;
        if (currentStatePointer != clinicalStateList.size() - 1) {
            clinicalStateList.subList(currentStatePointer + 1, clinicalStateList.size()).clear();
            commandList.subList(currentStatePointer + 1, commandList.size()).clear();
        }
        clinicalStateList.add(new CliniCal(currentCliniCal));
        commandList.add(command);
        currentStatePointer++;
    }

    /**
     * Restores the previous CliniCal application state.
     */
    public void undo() {
        cliniCal.resetData(clinicalStateList.get(--currentStatePointer));
    }

    /**
     * Restores the previously undone CliniCal application state.
     */
    public void redo() {
        cliniCal.resetData(clinicalStateList.get(++currentStatePointer));
    }

    /**
     * Returns true if there are CliniCal states available to undo.
     */
    public boolean canUndoCliniCal() {
        return currentStatePointer != 0;
    }

    /**
     * Returns true if there are CliniCal states available to redo.
     */
    public boolean canRedoCliniCal() {
        return currentStatePointer != clinicalStateList.size() - 1;
    }

    /**
     * Returns the input corresponding to the command that is undone.
     */
    public String getUndoCommand() {
        return commandList.get(currentStatePointer + 1);
    }

    /**
     * Returns the input corresponding to the command that is redone.
     */
    public String getRedoCommand() {
        return commandList.get(currentStatePointer);
    }

    /**
     * Returns the current state being pointed at for testing.
     */
    public ReadOnlyCliniCal getCurrentCliniCalState() {
        return clinicalStateList.get(currentStatePointer);
    }
}
