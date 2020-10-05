package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.animal.Animal;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withAnimal("John", "Doe").build();}
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
     * Adds a new {@code Animal} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withAnimal(Animal animal) {
        addressBook.addAnimal(animal);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
