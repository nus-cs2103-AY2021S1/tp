package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building AddressBook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class McGymmyBuilder {

    private AddressBook addressBook;

    public McGymmyBuilder() {
        addressBook = new AddressBook();
    }

    public McGymmyBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public McGymmyBuilder withPerson(Person person) {
        addressBook.addPerson(person);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
