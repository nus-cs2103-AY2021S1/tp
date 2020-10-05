package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.animal.Email;
import seedu.address.model.animal.Id;
import seedu.address.model.animal.Name;
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.Species;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Animal objects.
 */
public class AnimalBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_ID = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_SPECIES = "Panthera leo";

    private Name name;
    private Id id;
    private Email email;
    private Species species;
    private Set<Tag> tags;

    /**
     * Creates a {@code AnimalBuilder} with the default details.
     */
    public AnimalBuilder() {
        name = new Name(DEFAULT_NAME);
        id = new Id(DEFAULT_ID);
        email = new Email(DEFAULT_EMAIL);
        species = new Species(DEFAULT_SPECIES);
        tags = new HashSet<>();
    }

    /**
     * Initializes the AnimalBuilder with the data of {@code animalToCopy}.
     */
    public AnimalBuilder(Animal animalToCopy) {
        name = animalToCopy.getName();
        id = animalToCopy.getId();
        email = animalToCopy.getEmail();
        species = animalToCopy.getSpecies();
        tags = new HashSet<>(animalToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Animal} that we are building.
     */
    public AnimalBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Animal} that we are building.
     */
    public AnimalBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Species} of the {@code Animal} that we are building.
     */
    public AnimalBuilder withSpecies(String species) {
        this.species = new Species(species);
        return this;
    }

    /**
     * Sets the {@code Id} of the {@code Animal} that we are building.
     */
    public AnimalBuilder withId(String id) {
        this.id = new Id(id);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Animal} that we are building.
     */
    public AnimalBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Animal build() {
        return new Animal(name, id, email, species, tags);
    }

}
