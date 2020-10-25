package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

public class VersionedModuleList extends ModuleList {
    private List<ReadOnlyModuleList> moduleListStateList = new ArrayList<>();
    private int currentStatePointer;

    /**
     * Creates a versioned module list using the module lists in the {@code toBeCopied}
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
    public void commit(ModuleList moduleList) {
        moduleListStateList.subList(this.currentStatePointer + 1, moduleListStateList.size()).clear();
        moduleListStateList.add(new ModuleList(moduleList));
        this.currentStatePointer += 1;
    }

    /**
     * Restores the previous module list state from history.
     */
    public void undo() {
        assert !isIndexZero() : "Assertion error, there are no instructions to undo";
        this.currentStatePointer -= 1;
    }

    /**
     * Restores the previously undone module list state from history.
     */
    public void redo() {
        assert !isLastIndex() : "Assertion error, there are no instructions to redo";
        this.currentStatePointer += 1;
    }

    public boolean isIndexZero() {
        return currentStatePointer == 0;
    }

    public boolean isLastIndex() {
        return currentStatePointer >= moduleListStateList.size() - 1;
    }

    /**
     * Returns the module list the current state pointer is pointing to in the form
     * of an observable list
     */
    public ReadOnlyModuleList getCurrentModuleList() {
        return moduleListStateList.get(currentStatePointer);
    }
}
