package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Bidder;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class BidderAddressBook implements ReadOnlyBidderAddressBook {

    @Override
    public ObservableList<Bidder> getBidderList() {
        // TODO
        return null;
    }

}
