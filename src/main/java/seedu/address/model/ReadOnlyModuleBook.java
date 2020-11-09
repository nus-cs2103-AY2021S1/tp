package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.module.Module;

public interface ReadOnlyModuleBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Module> getModuleList();
}
