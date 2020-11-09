package seedu.medibook.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.medibook.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's ic in the medi book.
 * Guarantees: immutable; is valid as declared in {@link #isValidIc(String)}
 */
public class Ic {

    public static final String MESSAGE_CONSTRAINTS = "IC should be of the format @xxxxxxx#"
            + "and adhere to the following constraints:\n"
            + "1. The @ should be a letter S, T, F or G\n"
            + "2. The xxxxxxx should be a 7-digit number\n"
            + "3. The # should be any letter";

    public static final String FIRST_CHARACTER_REGEX = "[STFGstfg]{1,1}";
    public static final String DIGITS_REGEX = "\\d{7,7}";
    public static final String LAST_CHARACTER_REGEX = "\\w{1,1}";
    public static final String VALIDATION_REGEX = FIRST_CHARACTER_REGEX + DIGITS_REGEX + LAST_CHARACTER_REGEX;
    public final String ic;

    /**
     * Constructs an {@code Ic}.
     *
     * @param ic A valid IC number.
     */
    public Ic(String ic) {
        requireNonNull(ic);
        checkArgument(isValidIc(ic), MESSAGE_CONSTRAINTS);
        this.ic = ic.toUpperCase();
        assert ic.length() == 9;
    }

    /**
     * Returns if a given string is a valid IC number.
     */
    public static boolean isValidIc(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return ic;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Ic // instanceof handles nulls
                && ic.equals(((Ic) other).ic)); // state check
    }

    @Override
    public int hashCode() {
        return ic.hashCode();
    }
}
