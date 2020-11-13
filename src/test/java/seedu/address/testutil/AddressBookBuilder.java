package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withTag(TAG_NAME).build();}
 */
public class AddressBookBuilder {

    private AddressBook addressBook;

    public AddressBookBuilder() {
        addressBook = new AddressBook();
    }

    public AddressBookBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Tag} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withTag(Tag tag) {
        addressBook.addTag(tag);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
