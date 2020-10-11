package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Bidder;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyBidderAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Bidder> getBidderList();

}
