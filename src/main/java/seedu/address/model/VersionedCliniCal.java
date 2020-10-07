package seedu.address.model;

import java.util.ArrayList;

public class VersionedCliniCal extends CliniCal {

    private static final int INITIAL_POINTER = 0;

    private final ArrayList<ReadOnlyCliniCal> clinicalStateList;

    private final ArrayList<String> commandList;

    private final CliniCal cliniCal;

    private int currentStatePointer;

    /**
     * Initializes and saves the initial {@code CliniCal} state on running of CliniCal and assigns the
     * {@code currentStatePointer} to it.
     *
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
     * Returns true if the pointer is not at the oldest saved CliniCal state.
     */
    public boolean canUndoCliniCal() {
        return currentStatePointer != 0;
    }

    /**
     * Returns true if the pointer is not at the newest saved CliniCal state.
     */
    public boolean canRedoCliniCal() {
        return currentStatePointer != clinicalStateList.size() - 1;
    }

    public String getUndoCommand() {
        return commandList.get(currentStatePointer + 1);
    }

    public String getRedoCommand() {
        return commandList.get(currentStatePointer);
    }

}
