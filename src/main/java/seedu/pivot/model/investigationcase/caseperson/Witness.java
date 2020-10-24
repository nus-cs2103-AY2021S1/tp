package seedu.pivot.model.investigationcase.caseperson;

/**
 * Represents a Witness in the investigation case.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Witness extends CasePerson {

    /**
     * Constructs a {@code Witness}.
     *
     * @param name A valid name.
     */
    public Witness(Name name, Gender gender, Phone phone, Email email, Address address) {
        super(name, gender, phone, email, address);
    }

    /**
     * Returns true if both suspects have the same identity and data fields.
     * This defines a stronger notion of equality between two suspects.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Witness)) {
            return false;
        }

        Witness otherWitness = (Witness) other;
        return otherWitness.getName().equals(getName())
                && otherWitness.getGender().equals(getGender())
                && otherWitness.getPhone().equals(getPhone())
                && otherWitness.getEmail().equals(getEmail())
                && otherWitness.getAddress().equals(getAddress());
    }

}
