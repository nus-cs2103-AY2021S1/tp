package seedu.address.testutil;

<<<<<<< Updated upstream:src/test/java/seedu/address/testutil/AddressBookBuilder.java
import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
=======
import jimmy.mcgymmy.model.McGymmy;
import jimmy.mcgymmy.model.food.Food;

/**
 * A utility class to help with building McGymmy objects.
 * Example usage: <br>
 * {@code McGymmy ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
>>>>>>> Stashed changes:src/test/java/jimmy/mcgymmy/testutil/AddressBookBuilder.java
 */
public class AddressBookBuilder {

    private McGymmy mcGymmy;

    public AddressBookBuilder() {
        mcGymmy = new McGymmy();
    }

    public AddressBookBuilder(McGymmy mcGymmy) {
        this.mcGymmy = mcGymmy;
    }

    /**
     * Adds a new {@code Food} to the {@code McGymmy} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        mcGymmy.addPerson(person);
        return this;
    }

    public McGymmy build() {
        return mcGymmy;
    }
}
