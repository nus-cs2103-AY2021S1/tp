package seedu.pivot.model.investigationcase.caseperson;

/**
 * Represents a Suspect in the investigation case.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Suspect extends CasePerson {
    /**
     * Constructs a {@code Suspect}.
     *
     * @param name A valid name.
     */
    public Suspect(Name name, Gender gender, Phone phone, Email email, Address address) {
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

        if (!(other instanceof Suspect)) {
            return false;
        }

        Suspect otherSuspect = (Suspect) other;
        return otherSuspect.getName().equals(getName())
                && otherSuspect.getGender().equals(getGender())
                && otherSuspect.getPhone().equals(getPhone())
                && otherSuspect.getEmail().equals(getEmail())
                && otherSuspect.getAddress().equals(getAddress());
    }
}
