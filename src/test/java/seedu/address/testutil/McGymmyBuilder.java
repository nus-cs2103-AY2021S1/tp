package seedu.address.testutil;

import seedu.address.model.McGymmy;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building McGymmy objects.
 * Example usage: <br>
 *     {@code McGymmy ab = new McGymmyBuilder().withPerson("John", "Doe").build();}
 */
public class McGymmyBuilder {

    private McGymmy mcGymmy;

    public McGymmyBuilder() {
        mcGymmy = new McGymmy();
    }

    public McGymmyBuilder(McGymmy mcGymmy) {
        this.mcGymmy = mcGymmy;
    }

    /**
     * Adds a new {@code Person} to the {@code McGymmy} that we are building.
     */
    public McGymmyBuilder withPerson(Person person) {
        mcGymmy.addFood(person);
        return this;
    }

    public McGymmy build() {
        return mcGymmy;
    }
}
