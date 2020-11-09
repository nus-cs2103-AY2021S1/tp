package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.exceptions.VersionedListException;

/**
 * Class that stores versioned history and future of a contact list used for undo/redo functions
 */
public class VersionedContactList extends ContactList {
    private static final String MESSAGE_NO_REDO_HISTORY = "There are no Contact List commands to redo";
    private static final String MESSAGE_NO_UNDO_HISTORY = "There are no Contact List commands to undo";
    private List<ReadOnlyContactList> contactListStateList = new ArrayList<>();
    private int currentStatePointer;
    /**
     * Creates a versioned contact list with an empty initial contact list
     */
    public VersionedContactList() {
        super();
        contactListStateList.add(new ContactList());
        this.currentStatePointer = 0;
    }
    /**
     * Creates a versioned contact list using the contact list in the {@code toBeCopied}
     * @param toBeCopied
     */
    public VersionedContactList(ReadOnlyContactList toBeCopied) {
        super(toBeCopied);
        contactListStateList.add(toBeCopied);
        this.currentStatePointer = 0;
    }
    /**
     * Saves the current contact list state in history.
     */
    public void commit(ReadOnlyContactList contactList) {
        contactListStateList.subList(this.currentStatePointer + 1, contactListStateList.size()).clear();
        contactListStateList.add(new ContactList(contactList));
        this.currentStatePointer += 1;
        super.resetData(contactList);
    }

    /**
     * Restores the previous contact list state from history.
     */
    public void undo() throws VersionedListException {
        if (isIndexZero()) {
            throw new VersionedListException(MESSAGE_NO_UNDO_HISTORY);
        }
        this.currentStatePointer -= 1;
        super.resetData(contactListStateList.get(currentStatePointer));
    }

    /**
     * Restores the previously undone contact list state from history.
     */
    public void redo() throws VersionedListException {
        if (isLastIndex()) {
            throw new VersionedListException(MESSAGE_NO_REDO_HISTORY);
        }
        assert !isLastIndex() : "Assertion error, there are no instructions to redo";
        this.currentStatePointer += 1;
        super.resetData(contactListStateList.get(currentStatePointer));
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
        return currentStatePointer >= contactListStateList.size() - 1;
    }
}
