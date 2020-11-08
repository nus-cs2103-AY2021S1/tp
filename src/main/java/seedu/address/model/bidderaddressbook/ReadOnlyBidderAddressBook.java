package seedu.address.model.bidderaddressbook;

import javafx.collections.ObservableList;
import seedu.address.model.id.BidderId;
import seedu.address.model.person.bidder.Bidder;

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
