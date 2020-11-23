package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.Entity;
import seedu.address.model.Id;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Person implements Entity {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Gender gender;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Gender gender) {
        requireAllNonNull(name, phone, email, gender);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
    }
    //TODO: Fix this abstraction
    public abstract <T extends Id> T getId();

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Gender getGender() {
        return gender;
    }

    /**
     * Returns true if both persons have the same name and same phone number.
     * This defines a weaker notion of equality between two persons.
     * @param other the person to compare to.
     * @return true if both persons have the same name and phone number.
     */
    @Override
    public boolean isSame(Entity other) {
        if (other == this) {
            return true;
        } else if (other instanceof Person) {
            Person otherPerson = (Person) other;
            return otherPerson.getName().equals(getName())
                    && otherPerson.getPhone().equals(getPhone());
        } else {
            return false;
        }
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getGender().equals(getGender())
                && otherPerson.getId().equals(getId());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append((getGender().equals(Gender.MALE_GENDER) ? "Mr " : "Ms "))
                .append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail());
        return builder.toString();
    }

}
