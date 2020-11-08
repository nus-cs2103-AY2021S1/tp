package seedu.address.model.selleraddressbook;

import javafx.collections.ObservableList;
import seedu.address.model.id.SellerId;
import seedu.address.model.person.seller.Seller;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlySellerAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Seller> getSellerList();
}
