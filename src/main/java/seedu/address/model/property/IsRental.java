package seedu.address.model.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.List;

/**
 * Represents whether a property is a rental in the property book.
 * Guarantees: immutable; is valid as declared in {@link #isValidIsRental(String)}
 */
public class IsRental {

    public static final String MESSAGE_CONSTRAINTS =
            "isRental should be yes / y or no / n only.";

    public static final List<String> VALID_STRINGS = Arrays.asList("yes", "y", "no", "n");

    public final boolean isRental;

    /**
     * Constructs a {@code IsRental}.
     *
     * @param isRental A valid isRental.
     */
    public IsRental(String isRental) {
        requireNonNull(isRental);
        checkArgument(isValidIsRental(isRental), MESSAGE_CONSTRAINTS);
        String lowerCaseIsRental = isRental.toLowerCase();
        switch (lowerCaseIsRental) {
        case "yes":
        case "y":
            this.isRental = true;
            break;
        case "no":
        case "n":
            this.isRental = false;
            break;
        default:
            throw new AssertionError("Invalid isRental should be handled already.");
        }
    }

    /**
     * Returns true if a given string is a valid isRental.
     */
    public static boolean isValidIsRental(String test) {
        return VALID_STRINGS.stream().anyMatch(valid -> valid.equals(test.toLowerCase()));
    }

    /** Returns the boolean representation of whether the property is a rental. */
    public boolean isRental() {
        return isRental;
    }

    @Override
    public String toString() {
        return isRental ? "Yes" : "No";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IsRental // instanceof handles nulls
                && isRental == ((IsRental) other).isRental); // state check
    }

}
