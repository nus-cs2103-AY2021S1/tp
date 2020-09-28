package seedu.address.testutil;

import seedu.address.model.McGymmy;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code McGymmy ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private McGymmy addressBook;

    public AddressBookBuilder() {
        addressBook = new McGymmy();
    }

    public AddressBookBuilder(McGymmy addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code McGymmy} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        addressBook.addFood(person);
        return this;
    }

    public McGymmy build() {
        return addressBook;
    }
}
