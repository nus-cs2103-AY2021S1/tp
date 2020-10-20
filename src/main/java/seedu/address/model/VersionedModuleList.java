package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.module.Module;

import java.util.ArrayList;
import java.util.List;

public class VersionedModuleList extends ModuleList {
    private List<ObservableList<Module>> moduleListStateList = new ArrayList<>();
    private int currentStatePointer;

    /**
     * Creates a versioned module list using the module lists in the {@code toBeCopied}
     * @param toBeCopied
     */
    public VersionedModuleList(ReadOnlyModuleList toBeCopied) {
        super(toBeCopied);
        moduleListStateList.add(super.getModuleList());
        this.currentStatePointer = 0;
    }
    /**
     * Saves the current module list state in history.
     */
    public void commit(ModuleList moduleList) {
        moduleListStateList.add(moduleList.getModuleList());
        this.currentStatePointer += 1;
    }

    /**
     * Restores the previous module list state from history.
     */
    public void undo() {
        this.currentStatePointer -= 1;
    }

    /**
     * Restores the previously undone module list state from history.
     */
    public void redo() {
        this.currentStatePointer += 1;
    }

    public boolean isIndexZero() {
        return currentStatePointer == 0;
    }

    /**
     * Returns the module list the current state pointer is pointing to in the form
     * of an observable list
     */
    public ObservableList<Module> getCurrentModuleList() {
        return moduleListStateList.get(currentStatePointer);
    }
}
