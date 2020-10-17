package seedu.pivot.model.investigationcase;

public class Victim extends CasePerson {

    /**
     * Constructs a {@code Victim}.
     *
     * @param name A valid name.
     */
    public Victim(Name name) {
        super(name);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Victim // instanceof handles nulls
                && this.getName().equals(((Victim) other).getName())); // state check
    }
}
