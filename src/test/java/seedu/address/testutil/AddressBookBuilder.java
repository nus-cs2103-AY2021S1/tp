package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Assignment;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withAssignment("John", "Doe").build();}
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
     * Adds a new {@code Assignment} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withAssignment(Assignment assignment) {
        addressBook.addAssignment(assignment);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
