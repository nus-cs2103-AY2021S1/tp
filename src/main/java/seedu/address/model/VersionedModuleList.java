package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.exceptions.VersionedListException;

/**
 * Class that stores versioned history and future of a module list used for undo/redo functions
 */
public class VersionedModuleList extends ModuleList {
    public static final String MESSAGE_NO_REDO_HISTORY = "There are no Module List commands to redo";
    public static final String MESSAGE_NO_UNDO_HISTORY = "There are no Module List commands to undo";
    private List<ReadOnlyModuleList> moduleListStateList = new ArrayList<>();
    private int currentStatePointer;
    /**
     * Creates a versioned module list with an empty initial module list
     */
    public VersionedModuleList() {
        super(new ModuleList());
        moduleListStateList.add(new ModuleList());
        this.currentStatePointer = 0;
    }

    /**
     * Creates a versioned module list using the module list in the {@code toBeCopied}
     * @param toBeCopied
     */
    public VersionedModuleList(ReadOnlyModuleList toBeCopied) {
        super(toBeCopied);
        moduleListStateList.add(toBeCopied);
        this.currentStatePointer = 0;
    }
    /**
     * Saves the current module list state in history.
     */
    public void commit(ReadOnlyModuleList moduleList) {
        moduleListStateList.subList(this.currentStatePointer + 1, moduleListStateList.size()).clear();
        moduleListStateList.add(new ModuleList(moduleList));
        this.currentStatePointer += 1;
        super.resetData(moduleList);
    }

    /**
     * Restores the previous module list state from history.
     */
    public void undo() throws VersionedListException {
        if (isIndexZero()) {
            throw new VersionedListException(MESSAGE_NO_UNDO_HISTORY);
        }
        assert !isIndexZero() : "Assertion error, there are no instructions to undo";
        this.currentStatePointer -= 1;
        super.resetData(moduleListStateList.get(currentStatePointer));
    }

    /**
     * Restores the previously undone module list state from history.
     */
    public void redo() throws VersionedListException {
        if (isLastIndex()) {
            throw new VersionedListException(MESSAGE_NO_REDO_HISTORY);
        }
        assert !isLastIndex() : "Assertion error, there are no instructions to redo";
        this.currentStatePointer += 1;
        super.resetData(moduleListStateList.get(currentStatePointer));
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
        return currentStatePointer >= moduleListStateList.size() - 1;
    }
}
