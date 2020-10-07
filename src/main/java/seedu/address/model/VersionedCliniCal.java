package seedu.address.model;

import java.util.ArrayList;

public class VersionedCliniCal extends CliniCal {

    private static final int INITIAL_POINTER = 0;

    private final ArrayList<ReadOnlyCliniCal> clinicalStateList;

    private final CliniCal cliniCal;

    private int currentStatePointer;

    /**
     * Initializes and saves the {@code CliniCal} state on running of CliniCal and assigns the
     * {@code currentStatePointer} to it.
     *
     */
    public VersionedCliniCal(CliniCal initialCliniCal) {
        clinicalStateList = new ArrayList<>();
        cliniCal = initialCliniCal;
        currentStatePointer = INITIAL_POINTER;
        clinicalStateList.add(cliniCal);
    }

    /**
     * Saves the current CliniCal application state in its history, after purging redundant states.
     */
    public void commit(ReadOnlyCliniCal currentCliniCal) {
        if (currentStatePointer != clinicalStateList.size() - 1) {
            clinicalStateList.subList(currentStatePointer + 1, clinicalStateList.size()).clear();
        }
        clinicalStateList.add(currentCliniCal);
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

}
