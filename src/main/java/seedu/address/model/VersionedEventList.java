package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.exceptions.VersionedListException;

public class VersionedEventList extends EventList {
    private static final String MESSAGE_NO_REDO_HISTORY = "There are no Event List commands to redo";
    private static final String MESSAGE_NO_UNDO_HISTORY = "There are no Event List commands to undo";
    private List<ReadOnlyEventList> eventListStateList = new ArrayList<>();
    private int currentStatePointer;
    /**
     * Creates a versioned event list using the event list in the {@code toBeCopied}
     * @param toBeCopied
     */
    public VersionedEventList(ReadOnlyEventList toBeCopied) {
        super(toBeCopied);
        eventListStateList.add(toBeCopied);
        this.currentStatePointer = 0;
    }
    /**
     * Saves the current module list state in history.
     */
    public void commit(ReadOnlyEventList eventList) {
        eventListStateList.subList(this.currentStatePointer + 1, eventListStateList.size()).clear();
        eventListStateList.add(new EventList(eventList));
        this.currentStatePointer += 1;
    }

    /**
     * Restores the previous event list state from history.
     */
    public void undo() throws VersionedListException {
        if (isIndexZero()) {
            throw new VersionedListException(MESSAGE_NO_UNDO_HISTORY);
        }
        this.currentStatePointer -= 1;
    }

    /**
     * Restores the previously undone event list state from history.
     */
    public void redo() throws VersionedListException {
        if (isLastIndex()) {
            throw new VersionedListException(MESSAGE_NO_REDO_HISTORY);
        }
        assert !isLastIndex() : "Assertion error, there are no instructions to redo";
        this.currentStatePointer += 1;
    }

    /**
     * Returns true if state pointer is at 0.
     */
    public boolean isIndexZero() {
        return currentStatePointer == 0;
    }

    /**
     * Returns true if state pointer greater than the size of the eventList state list
     */
    public boolean isLastIndex() {
        return currentStatePointer >= eventListStateList.size() - 1;
    }

    /**
     * Returns the event list the current state pointer is pointing to in the form
     * of an observable list
     */
    public ReadOnlyEventList getCurrentEventList() {
        return eventListStateList.get(currentStatePointer);
    }
}
