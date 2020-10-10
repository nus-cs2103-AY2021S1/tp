package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Seller;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlySellerAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Seller> getPersonList();

}
