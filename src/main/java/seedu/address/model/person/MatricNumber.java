package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Matric number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidMatricNumber(String)}
 */
public class MatricNumber {

    public static final String MESSAGE_CONSTRAINTS =
            "Matric numbers should only contain numbers,"
                    + " and it should be in the form A<7 digits><1 uppercase alphabet>.";
    public static final String VALIDATION_REGEX = "^A\\d{7}[A-Z]$";
    public final String value;

    /**
     * Constructs a {@code MatricNumber}.
     *
     * @param matricNumber A valid matric number.
     */
    public MatricNumber(String matricNumber) {
        requireNonNull(matricNumber);
        checkArgument(isValidMatricNumber(matricNumber), MESSAGE_CONSTRAINTS);
        value = matricNumber;
    }

    /**
     * Returns true if a given string is a valid matric number.
     */
    public static boolean isValidMatricNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }
    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MatricNumber // instanceof handles nulls
                && value.equals(((MatricNumber) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
