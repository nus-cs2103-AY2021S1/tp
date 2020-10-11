package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.module.Module;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the Modules list.
     * This list will not contain any duplicate Module.
     */
    ObservableList<Module> getModuleList();

}
