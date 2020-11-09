package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.information.Job;

/**
 * Unmodifiable view of a person address book
 */
public interface ReadOnlyJobAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Job> getJobList();

}
