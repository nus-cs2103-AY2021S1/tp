package seedu.pivot.model.investigationcase.caseperson;

/**
 * Represents a Victim in the investigation case.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Victim extends CasePerson {

    /**
     * Constructs a {@code Victim}.
     *
     * @param name A valid name.
     */
    public Victim(Name name, Sex sex, Phone phone, Email email, Address address) {
        super(name, sex, phone, email, address);
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

        if (!(other instanceof Victim)) {
            return false;
        }

        Victim otherVictim = (Victim) other;
        return otherVictim.getName().equals(getName())
                && otherVictim.getSex().equals(getSex())
                && otherVictim.getPhone().equals(getPhone())
                && otherVictim.getEmail().equals(getEmail())
                && otherVictim.getAddress().equals(getAddress());
    }
}
