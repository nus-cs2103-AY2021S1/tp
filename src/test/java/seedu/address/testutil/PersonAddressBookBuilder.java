package seedu.address.testutil;

import seedu.address.model.PersonAddressBook;
import seedu.address.model.information.Person;

/**
 * A utility class to help with building PersonAddressBook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class PersonAddressBookBuilder {

    private PersonAddressBook personAddressBook;

    public PersonAddressBookBuilder() {
        personAddressBook = new PersonAddressBook();
    }

    public PersonAddressBookBuilder(PersonAddressBook personAddressBook) {
        this.personAddressBook = personAddressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public PersonAddressBookBuilder withPerson(Person person) {
        personAddressBook.addPerson(person);
        return this;
    }

    public PersonAddressBook build() {
        return personAddressBook;
    }
}
