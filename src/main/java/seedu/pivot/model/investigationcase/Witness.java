package seedu.pivot.model.investigationcase;

/**
 * Represents a Witness in the investigation case.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Witness extends CasePerson {

    /**
     * Every field must be present and not null.
     */
    public Witness(Name name) {
        super(name);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Witness)) {
            return false;
        }

        Witness otherWitness = (Witness) other;
        return otherWitness.getName().equals(getName());
    }

}
