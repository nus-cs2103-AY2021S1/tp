package seedu.address.testutil;

import seedu.address.model.Projact;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building Projact objects.
 * Example usage: <br>
 *     {@code Projact ab = new ProjactBuilder().withPerson("John", "Doe").build();}
 */
public class ProjactBuilder {

    private Projact projact;

    public ProjactBuilder() {
        projact = new Projact();
    }

    public ProjactBuilder(Projact projact) {
        this.projact = projact;
    }

    /**
     * Adds a new {@code Person} to the {@code Projact} that we are building.
     */
    public ProjactBuilder withPerson(Person person) {
        projact.addPerson(person);
        return this;
    }

    /**
     * Adds a new {@code Tag} to the {@code Projact} that we are building.
     */
    public ProjactBuilder withTag(Tag tag) {
        projact.addTag(tag);
        return this;
    }

    public Projact build() {
        return projact;
    }
}
