package seedu.address.model.animal;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Animal in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Animal {

    // Identity fields
    private final Name name;
    private final Id id;

    // Data fields
    private final Species species;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Animal(Name name, Id id, Species species, Set<Tag> tags) {
        requireAllNonNull(name, id, species, tags);
        this.name = name;
        this.id = id;
        this.species = species;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Id getId() {
        return id;
    }

    public Species getSpecies() {
        return species;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both animals of the same name and id.
     * This defines a weaker notion of equality between two animals.
     */
    public boolean isSameAnimal(Animal otherAnimal) {
        if (otherAnimal == this) {
            return true;
        }

        return otherAnimal != null
                && otherAnimal.getName().equals(getName())
                && otherAnimal.getId().equals(getId());
    }

    /**
     * Returns true if both animals have the same identity and data fields.
     * This defines a stronger notion of equality between two animals.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Animal)) {
            return false;
        }

        Animal otherAnimal = (Animal) other;
        return otherAnimal.getName().equals(getName())
                && otherAnimal.getId().equals(getId())
                && otherAnimal.getSpecies().equals(getSpecies())
                && otherAnimal.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, id, species, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" ID: ")
                .append(getId())
                .append(" Species: ")
                .append(getSpecies())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
