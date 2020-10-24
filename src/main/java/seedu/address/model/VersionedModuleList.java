package seedu.address.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VersionedModuleList extends ModuleList {
    private List<ReadOnlyModuleList> moduleListStateList = new ArrayList<>();
    private Optional<ReadOnlyModuleList> redoStatePointer;
    private int currentStatePointer;

    /**
     * Creates a versioned module list using the module lists in the {@code toBeCopied}
     * @param toBeCopied
     */
    public VersionedModuleList(ReadOnlyModuleList toBeCopied) {
        super(toBeCopied);
        moduleListStateList.add(toBeCopied);
        this.currentStatePointer = 0;
        this.redoStatePointer = Optional.empty();
    }
    /**
     * Saves the current module list state in history.
     */
    public void commit(ModuleList moduleList) {
        moduleListStateList.add(moduleList);
        this.currentStatePointer += 1;
        this.redoStatePointer = Optional.empty();
    }

    /**
     * Restores the previous module list state from history.
     */
    public void undo() {
        redoStatePointer = Optional.of(moduleListStateList.remove(this.currentStatePointer));
        this.currentStatePointer -= 1;
    }

    /**
     * Restores the previously undone module list state from history.
     */
    public void redo() {
        moduleListStateList.add(redoStatePointer.get());
        this.currentStatePointer += 1;
    }

    public boolean isIndexZero() {
        return currentStatePointer == 0;
    }

    /**
     * Returns the module list the current state pointer is pointing to in the form
     * of an observable list
     */
    public ReadOnlyModuleList getCurrentModuleList() {
        return moduleListStateList.get(currentStatePointer);
    }
}
