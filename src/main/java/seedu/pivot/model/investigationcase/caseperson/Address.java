package seedu.pivot.model.investigationcase.caseperson;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's address in PIVOT.
 * Guarantees: immutable; is always valid
 */
public class Address {

    public final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param address A valid address.
     */
    public Address(String address) {
        requireNonNull(address);
        value = address.trim();
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && value.equals(((Address) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

