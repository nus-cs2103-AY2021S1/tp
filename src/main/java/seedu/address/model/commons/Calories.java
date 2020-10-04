package seedu.address.model.commons;

import static java.util.Objects.requireNonNull;

public class Calories {
    public static final String MESSAGE_CONSTRAINTS =
            "Calories should only contain Integer number, and it should not be blank";
    public final Integer value;

    /**
     * Constructs a {@code Calories}.
     *
     * @param calories A valid ingredients number.
     */
    public Calories(Integer calories) {
        requireNonNull(calories);
        value = calories;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Calories // instanceof handles nulls
                && value.equals(((Calories) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
