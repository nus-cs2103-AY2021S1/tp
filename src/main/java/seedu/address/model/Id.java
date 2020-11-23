package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an ID.
 * Guarantees: immutable; is valid as declared in {@link #isValidId(String)}
 */
public abstract class Id {

    public static final String MESSAGE_CONSTRAINTS =
            "ID must a positive integer.";
    protected final int id;

    /**
     * Constructs a {@code PersonId}.
     *
     * @param id a valid person ID.
     */
    public Id(String id) {
        requireNonNull(id);
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        this.id = Integer.parseInt(id);
    }

    public Id(int id) {
        this.id = id;
    }

    /**
     * Getter for id attribute
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns true if a given string is a valid person ID.
     */
    public static boolean isValidId(String test) {
        int positiveNumber;
        try {
            positiveNumber = Integer.parseInt(test);
        } catch (NumberFormatException e) {
            return false;
        }

        if (positiveNumber > 0) {
            return true;

        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Id // instanceof handles nulls
                && this.id == ((Id) other).id); // state check
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }


    @Override
    public String toString() {
        return Integer.toString(id);
    }
}
