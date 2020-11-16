package seedu.address.testutil;

import seedu.address.model.Tasker;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code Tasker ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private Tasker tasker;

    public AddressBookBuilder() {
        tasker = new Tasker();
    }

    public AddressBookBuilder(Tasker tasker) {
        this.tasker = tasker;
    }

    /**
     * Adds a new {@code Person} to the {@code Tasker} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        tasker.addPerson(person);
        return this;
    }

    public Tasker build() {
        return tasker;
    }
}
