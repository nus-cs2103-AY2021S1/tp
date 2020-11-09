package seedu.address.testutil;

import seedu.address.model.PersonBook;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Personbook objects.
 * Example usage: <br>
 *     {@code PersonBook ab = new PersonBookBuilder().withPerson("John", "Doe").build();}
 */
public class PersonBookBuilder {

    private PersonBook personBook;

    public PersonBookBuilder() {
        personBook = new PersonBook();
    }

    public PersonBookBuilder(PersonBook addressBook) {
        this.personBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code PersonBook} that we are building.
     */
    public PersonBookBuilder withPerson(Person person) {
        personBook.addPerson(person);
        return this;
    }

    public PersonBook build() {
        return personBook;
    }
}
