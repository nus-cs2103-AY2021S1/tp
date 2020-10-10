package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Seller;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class SellerAddressBook implements ReadOnlySellerAddressBook {

    @Override
    public ObservableList<Seller> getPersonList() {
        // TODO
        return null;
    }

}
