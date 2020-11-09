package seedu.address.testutil.seller;

import seedu.address.model.person.seller.Seller;
import seedu.address.model.selleraddressbook.SellerAddressBook;

/**
 * A utility class to help with building SellerAddressbook objects.
 * Example usage: <br>
 *     {@code SellerAddressBook sb = new SellerAddressBookBuilder().withSeller("John", "Doe").build();}
 */
public class SellerAddressBookBuilder {

    private SellerAddressBook sellerAddressBook;

    public SellerAddressBookBuilder() {
        sellerAddressBook = new SellerAddressBook();
    }

    public SellerAddressBookBuilder(SellerAddressBook sellerAddressBook) {
        this.sellerAddressBook = sellerAddressBook;
    }

    /**
     * Adds a new {@code Seller} to the {@code SellerAddressBook} that we are building.
     */
    public SellerAddressBookBuilder withSeller(Seller seller) {
        sellerAddressBook.addSeller(seller);
        return this;
    }

    public SellerAddressBook build() {
        return sellerAddressBook;
    }
}
